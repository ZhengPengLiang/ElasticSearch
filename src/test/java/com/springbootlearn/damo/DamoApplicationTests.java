package com.springbootlearn.damo;

import com.springbootlearn.damo.Entity.Doc;
import com.springbootlearn.damo.service.ITikaService;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DamoApplication.class)
public class DamoApplicationTests {

    @Autowired
    private ITikaService tikaService;


    @Test
    public void tikaServiceTest(){
        File file=new File("D:/code/esfilesearch/src/main/resources/files/2016中国人工智能大会 大咖云集探讨人工智能.doc");
        System.out.println(file);
        Doc doc=tikaService.parserExtraction(file);
        System.out.println(doc.getTitle());
        System.out.println(doc.getFileContent());
    }

    

}
