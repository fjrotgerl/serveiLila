package com.esliceu.parser.controllers;

import com.esliceu.parser.component.ParseProcessor;
import com.esliceu.parser.component.Xmlparse;
import com.esliceu.parser.model.comunication.DataContainer;
import com.esliceu.parser.model.database.Student;
import com.esliceu.parser.model.xml.Center;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class PurpleController {

    @Autowired
    Xmlparse xmlparse;

    @Autowired
    ParseProcessor parseProcessor;

    @Autowired
    DataContainer dataContainer;

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

                    dataContainer.poblateData();

                    RestTemplate restTemplate = new RestTemplate();
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<Object> requestEntity = new HttpEntity<Object>(dataContainer,headers);
                    restTemplate.put("http://localhost:8080/groc",dataContainer);

                    return "Se ha subido el archivo de forma correcta y ahora se esta actualizando la base de datos" + fileName + "!";
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


    @RequestMapping(value="/groc",method = RequestMethod.PUT)
    public void  MockGroc(@RequestParam("Students") DataContainer dataContainer){

        System.out.println(dataContainer);
    }



}
