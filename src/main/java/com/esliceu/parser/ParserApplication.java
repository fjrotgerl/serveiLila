package com.esliceu.parser;

import com.esliceu.parser.component.ParseProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParserApplication.class, args);
    }

    @Autowired
    ParseProcessor parseProcessor;

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            //parseProcessor.init();
        };
    }
}