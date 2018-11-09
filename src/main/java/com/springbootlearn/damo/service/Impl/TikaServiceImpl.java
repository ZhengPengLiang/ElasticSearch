package com.springbootlearn.damo.service.Impl;

import com.springbootlearn.damo.Entity.Doc;
import com.springbootlearn.damo.service.ITikaService;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


@Service("tikaService")
public class TikaServiceImpl implements ITikaService {
    /**
     * 解析文档内容
     */
    public Doc parserExtraction(File file){
        //接受文档内容
        String fileContent="";
        BodyContentHandler handler=new BodyContentHandler();
        //自动解析器接口
        Parser parser=new AutoDetectParser();
        Metadata metadata=new Metadata();

        try{
            FileInputStream inputStream=new FileInputStream(file);
            ParseContext context=new ParseContext();
            //返回内容
            parser.parse(inputStream,handler,metadata,context);
            fileContent=handler.toString();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //最后返回文件名称和文件内容
        return new Doc(file.getName(),fileContent);
    }

}
