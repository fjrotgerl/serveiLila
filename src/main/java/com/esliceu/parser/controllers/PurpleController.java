package com.esliceu.parser.controllers;

import com.esliceu.parser.component.ParseProcessor;
import com.esliceu.parser.component.Xmlparse;
import com.esliceu.parser.model.comunication.DataContainer;
import com.esliceu.parser.model.database.StudentSession;
import com.esliceu.parser.model.xml.Center;
import com.esliceu.parser.repository.SessionStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.bind.JAXBException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class PurpleController {

    @Autowired
    Xmlparse xmlparse;

    @Autowired
    ParseProcessor parseProcessor;

    @Autowired
    DataContainer dataContainer;

    @Autowired
    SessionStudentRepository sessionStudentRepository;

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

                    try {

                        CompletableFuture<DataContainer> dataContainerCompletableFuture = parseProcessor.init();
                        dataContainer = dataContainerCompletableFuture.get();

                    } catch (InterruptedException e) {

                        return "Ha habido un error para actualizar la base de datos";

                    } catch (ExecutionException e) {

                        return "No se ha podido resolver el objeto contenedor de daots";
                    }


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

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(dataContainer.getPage())
                .queryParam("start",20)
                .queryParam("end", 20);

        restTemplate.getForEntity(builder.toUriString(),String.class);


        return "works";
    }

    @RequestMapping(value="/studentSessions",method = RequestMethod.GET)
    public String pagination(@RequestParam(value = "start") Integer page,@RequestParam(value = "end") Integer pageEnd){

        System.out.println(page);
        System.out.println(pageEnd);

        List<StudentSession> students = sessionStudentRepository.findAllByOrderById(PageRequest.of(page,pageEnd));

        System.out.println(students);
        return "workkkkk";


    }



}
