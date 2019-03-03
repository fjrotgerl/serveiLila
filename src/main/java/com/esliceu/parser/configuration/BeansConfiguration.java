package com.esliceu.parser.configuration;

import com.esliceu.parser.model.comunication.DataContainer;
import com.esliceu.parser.model.database.*;
import com.esliceu.parser.utils.EndTimeParser;
import com.esliceu.parser.utils.TimeParser;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.File;

@Configuration
public class BeansConfiguration {

    @Bean
    @Scope("prototype")
    public Group group() {
        return new Group();
    }

    @Bean
    @Scope("prototype")
    public Professor professor() {
        return new Professor();
    }

    @Bean
    @Scope("prototype")
    public ProfessorSession professorSession() {
        return new ProfessorSession();
    }

    @Bean
    @Scope("prototype")
    public Student student() {
        return new Student();
    }

    @Bean
    @Scope("prototype")
    public StudentSession studentSession() {
        return new StudentSession();
    }

    @Bean
    @Scope("prototype")
    public SchoolRoom aula(){ return new SchoolRoom();}

    @Bean
    @Scope("prototype")
    public DateTime hourFormat(){
        return new DateTime();
    }

    @Bean
    @Scope("prototype")
    public TimeParser timeCalculator(){ return new EndTimeParser();}


    @Value("${files.xml.classpath}")
    private String patch;

    @Value("${files.xml}")
    private String fileName;

    @Bean
    public File file() {
        return new File(patch + fileName);
    }

}
