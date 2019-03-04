package com.esliceu.parser.controllers;

import com.esliceu.parser.component.ParseProcessor;
import com.esliceu.parser.component.Xmlparse;
import com.esliceu.parser.model.comunication.DataContainer;
import com.esliceu.parser.model.xml.Center;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

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


                    BodyInserter<DataContainer, ReactiveHttpOutputMessage> inserter3
                            = BodyInserters.fromObject(dataContainer);

                    WebClient client3 = WebClient
                            .builder()
                            .baseUrl("http://localhost:8080")
                            .build();

                    WebClient.RequestBodySpec uri1 = client3
                            .method(HttpMethod.PUT)
                            .uri("/groc");

                   String response1 = uri1
                            .body(inserter3)
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();

                    System.out.println(response1);



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
    public String  MockGroc(@RequestBody DataContainer dataContainer){

        System.out.println(dataContainer);

        return "works";
    }



}
