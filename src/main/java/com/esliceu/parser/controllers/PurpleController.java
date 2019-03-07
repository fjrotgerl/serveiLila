package com.esliceu.parser.controllers;

import com.esliceu.parser.component.ParseProcessor;
import com.esliceu.parser.component.Xmlparse;
import com.esliceu.parser.model.comunication.AsyncProcessor;
import com.esliceu.parser.model.comunication.DataContainer;
import com.esliceu.parser.model.database.StudentSession;
import com.esliceu.parser.repository.SessionStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class PurpleController {

    @Autowired
    private Xmlparse xmlparse;

    @Autowired
    private ParseProcessor parseProcessor;

    @Autowired
    private DataContainer dataContainer;

    @Autowired
    private SessionStudentRepository sessionStudentRepository;

    @Autowired
    private AsyncProcessor asyncProcessor;

    @Autowired
    private RestTemplate restTemplate;


    @Value("${files.xml.classpath}")
    private String path;

    @Value("${files.xml}")
    private String fileName;

    @Value("${enpoint.blau}")
    private String endBlau;

    @Value("${ip.blau}")
    private String ipBlau;


    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public @ResponseBody String provideUploadInfor(){
        return "Puedes subir un archivo haciendo un post a esta misma URL.";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {

                byte[] bytes = file.getBytes();

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path + fileName)));
                stream.write(bytes);
                stream.close();

                asyncProcessor.updateDatabaseAsync();

                return "Se ha subido el archivo de forma correcta y ahora se esta actualizando la base de datos" + fileName + "!";

            } catch (FileNotFoundException e) {

                return "No se ha subido ningun archivo"+ e;

            } catch (IOException e) {

                return "Ha habido un problema al leer el archivo" + e;

            } catch (InterruptedException e) {

                return "Ha habido un problema al recuperar datos de la base" +e ;

            } catch (ExecutionException e) {

                return "Ha habido un problema al parsear el XML" +e ;

            } catch (JAXBException e) {

                return "Ha habido un problema cogiendo el objeto central del XML" +e;
            }

        } else {

            return "Ha habido un fallo al subir el archivo por un error de file.isEmpty por favor avise al administrador del sistmema";
        }
    }

    @RequestMapping(value="/studentSessions",method = RequestMethod.GET)
    public List<StudentSession> pagination(@RequestParam(value = "start") Integer page, @RequestParam(value = "end") Integer pageEnd){

        List<StudentSession> students = sessionStudentRepository.findAllByOrderById(PageRequest.of(page,pageEnd));

        return students;


    }


    @RequestMapping(value = "/getXML",method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getXML(@RequestHeader(value = "Authorization") String token){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authentication", token);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

        HttpEntity<?> httpEntity = new HttpEntity<Object>(body, headers);

        ResponseEntity<Resource> responseEntity = restTemplate.exchange( ipBlau+endBlau, HttpMethod.GET,httpEntity, Resource.class );

        InputStream responseInputStream;
        try {
            responseInputStream = responseEntity.getBody().getInputStream();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

         return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream")).header("Content-Disposition", "attachment; filename=" + fileName).body(new InputStreamResource(responseInputStream));


    }

}
