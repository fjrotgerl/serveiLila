package com.esliceu.parser.controllers;

import com.esliceu.parser.component.ParseProcessor;
import com.esliceu.parser.component.Xmlparse;
import com.esliceu.parser.model.database.Student;
import com.esliceu.parser.model.xml.Center;
import com.esliceu.parser.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

@RestController
public class PurpleController {

    @Autowired
    Xmlparse xmlparse;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private SessionProfessorRepository sessionProfessorRepository;

    @Autowired
    private SessionStudentRepository sessionStudentRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    ParseProcessor parseProcessor;

    @Value("${files.xml.classpath}")
    private String path;

    @Value("${files.xml}")
    private String fileName;


    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public @ResponseBody String provideUploadInfor(){
        return "Puedes subir un archivo haciendo un post a esta misma URL.";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()){
            try {

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path+fileName)));
                stream.write(bytes);
                stream.close();
                try {
                    parseProcessor.init();

                    Iterable<Student> students = studentRepository.findAll();
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.postForLocation("http://localhost:8080/groc",students);

                    return "Se ha subido el archivo de forma correcta actualizando la base de datos" + fileName + "!";
                } catch (JAXBException e) {
                    return "Ha habido un fallo al intentar actualizar la base de datos";
                }

            } catch (IOException e) {
                return "Ha habido un fallo al subir el archivo" + e.getMessage();
            }
        }

        else {
            return "Ha habido un fallo al subir el archivo por un error de file.isEmpty por favor avise al administrador del sistmema";
        }

    }

    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public Center center() throws JAXBException {
        return this.xmlparse.getData();
    }


    @RequestMapping(value="/groc",method = RequestMethod.POST)
    public void  MockGroc(@RequestParam("Students") Iterable<Student> students){

        System.out.println(students);
    }



}
