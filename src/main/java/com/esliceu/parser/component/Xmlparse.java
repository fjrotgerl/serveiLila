package com.esliceu.parser.component;

import com.esliceu.parser.model.xml.Center;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
public class Xmlparse {

    public Center getData() throws JAXBException {
        File file = new File("src/main/resources/exportacioDadesCentre.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Center.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return  (Center) jaxbUnmarshaller.unmarshal(file);
    }
}
