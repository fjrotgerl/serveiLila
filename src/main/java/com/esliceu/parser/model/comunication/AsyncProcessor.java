package com.esliceu.parser.model.comunication;


import com.esliceu.parser.component.ParseProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.xml.bind.JAXBException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class AsyncProcessor {

    @Autowired
    ParseProcessor parseProcessor;

    @Autowired
    DataContainer dataContainer;

    @Value("${endpoint.groc}")
    String endpointGroc;

    @Value("${ip.groc}")
    String ipGroc;

    public AsyncProcessor() {
    }

    @Async("asyncExecutor")
    public void updateDatabaseAsync() throws JAXBException, InterruptedException, ExecutionException {


        CompletableFuture<DataContainer> dataContainerCompletableFuture = parseProcessor.init();

        dataContainer = dataContainerCompletableFuture.get();

        BodyInserter<DataContainer, ReactiveHttpOutputMessage> inserter3
                = BodyInserters.fromObject(dataContainer);

        WebClient client3 = WebClient
                .builder()
                .baseUrl(ipGroc)
                .build();

        WebClient.RequestBodySpec uri1 = client3
                .method(HttpMethod.PUT)
                .uri(endpointGroc);

        String response1 = uri1
                .body(inserter3)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(response1);


    }

}
