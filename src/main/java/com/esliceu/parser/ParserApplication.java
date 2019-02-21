package com.esliceu.parser;

import com.esliceu.parser.model.database.Group;
import com.esliceu.parser.model.database.Student;
import com.esliceu.parser.repository.GroupRepository;
import com.esliceu.parser.repository.StudentRepository;
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
    public CommandLineRunner demo(StudentRepository stRepository, GroupRepository grRepository, ApplicationContext app) {
        return (args) -> {
            // save a couple of customer
            Student student = app.getBean(Student.class);
            student.setName("pollo");
            student.setCode("55");

            Group grup=  new Group(12563);
            grRepository.save(grup);
            student.setGroup(grup);
            stRepository.save(student);

            stRepository.save(new Student("Jack","1"));
            stRepository.save(new Student("Chloe","2"));
            stRepository.save(new Student("Kim","3"));
            stRepository.save(new Student("David","4"));
            stRepository.save(new Student("Michelle","5"));

            // fetch all customers
            System.out.println("Customers found with findAll():");
            System.out.println("-------------------------------");
            for (Student st : stRepository.findAll()) {
                System.out.println(st.getName());

            }
        };
    }

}
