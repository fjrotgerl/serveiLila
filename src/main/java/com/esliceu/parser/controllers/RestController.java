package com.esliceu.parser.controllers;

import com.esliceu.parser.component.Xmlparse;
import com.esliceu.parser.model.xml.Center;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.bind.JAXBException;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    Xmlparse xmlparse;

    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public Center center() throws JAXBException {
        return this.xmlparse.getData();
    }
}
