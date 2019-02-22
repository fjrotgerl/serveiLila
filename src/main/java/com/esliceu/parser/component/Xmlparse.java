package com.esliceu.parser.component;

import com.esliceu.parser.model.xml.Center;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
public class Xmlparse {

    private final File file;

    @Autowired
    public Xmlparse(File file) {
        this.file = file;
    }

    public Center getData() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Center.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return  (Center) jaxbUnmarshaller.unmarshal(file);
    }
}
