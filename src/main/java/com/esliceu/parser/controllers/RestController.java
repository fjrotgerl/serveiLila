package com.esliceu.parser.controllers;

import com.esliceu.parser.component.Xmlparse;
import com.esliceu.parser.model.xml.Center;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    Xmlparse xmlparse;

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
                return "Se ha subido el archivo de forma correcta" + fileName + "!";

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
}
