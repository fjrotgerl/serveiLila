package com.esliceu.parser.component;

import com.esliceu.parser.model.database.Group;
import com.esliceu.parser.model.database.Student;
import com.esliceu.parser.model.xml.Center;
import com.esliceu.parser.repository.GroupRepository;
import com.esliceu.parser.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;

@Component
public class ParseProcessor {

    @Autowired
    private Xmlparse parser;

    @Autowired
    private Student student;

    @Autowired
    private GroupRepository grRepository;

    @Autowired
    private StudentRepository stRepository;

    public Xmlparse getParser() {
        return parser;
    }

    public void setParser(Xmlparse parser) {
        this.parser = parser;
    }

    public void init() throws JAXBException {
        // save a couple of customer

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

        //Prueba de funcionamiento
        Center a = parser.getData();
        for (int i = 0; i <a.getAlumnes().size() ; i++) {
            for (int j = 0; j <a.getAlumnes().get(i).getAlumnes().size() ; j++) {
                System.out.println(a.getAlumnes().get(i).getAlumnes().get(j).getName()+" "+a.getAlumnes().get(i).getAlumnes().get(j).getFirstSurname());
            }
        }
    }
}
