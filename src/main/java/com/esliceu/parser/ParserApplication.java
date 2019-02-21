package com.esliceu.parser;

import com.esliceu.parser.model.Group;
import com.esliceu.parser.model.Student;
import com.esliceu.parser.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class ParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParserApplication.class, args);
    }

    @Bean
    @Scope("prototype")
    public Student student() {
        return new Student();
    }


    @Bean
    public CommandLineRunner demo(StudentRepository repository, ApplicationContext app) {
        return (args) -> {
            // save a couple of customer
//            Student student = app.getBean(Student.class);
//            student.setName("pollo");
//            student.setCode("55");
//            student.setGroup(new Group("grup"));
//            repository.save(student);
//
//            repository.save(new Student("Jack","1"));
//            repository.save(new Student("Chloe","2"));
//            repository.save(new Student("Kim","3"));
//            repository.save(new Student("David","4"));
//            repository.save(new Student("Michelle","5"));
//
//            // fetch all customers
//            System.out.println("Customers found with findAll():");
//            System.out.println("-------------------------------");
//            for (Student st : repository.findAll()) {
//                System.out.println(st.getName());
//
//            }
        };
    }

}
