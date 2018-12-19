package com.springbootlearn.damo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootlearn.damo.Entity.Doc;
import com.springbootlearn.damo.config.ESConfig;
import com.springbootlearn.damo.service.IESRestfulService;
import com.springbootlearn.damo.service.ITikaService;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DamoApplication.class)
public class DamoApplicationTests {

    @Autowired
    private ITikaService tikaService;

    @Autowired
    private IESRestfulService restfulService;

    @Autowired
    private  ESConfig esConfig;


    @Test
    public void tikaServiceTest(){
        File file=new File("D:/code/esfilesearch/src/main/resources/files/2016中国人工智能大会 大咖云集探讨人工智能.doc");
        System.out.println(file);
        Doc doc=tikaService.parserExtraction(file);
        System.out.println(doc.getTitle());
        System.out.println(doc.getFileContent());
    }


    @Test
    public void initIndexTest(){
        //索引名称-数据库名称
        String indexName="userdoc";
        //类型名--表名称
        String typeName="file";
        //分片数
        int shardNum=5;
        //副本数
        int replicNum=1;

        //设置mapping
        XContentBuilder builder=null;
        try {
            builder= XContentFactory.jsonBuilder();
            builder.startObject();
            {
                builder.startObject("properties");
                {
                    builder.startObject("title");
                    {
                        builder.field("type","text");
                        builder.field("analyzer","ik_max_word");
                    }
                    builder.endObject();
                    builder.startObject("filecontent");
                    {
                        builder.field("type","text");
                        builder.field("analyzer","ik_max_word");
                        builder.field("term_vector","with_positions_offsets");
                    }
                    builder.endObject();
                }
                builder.endObject();
            }
            builder.endObject();

            boolean isSuccess=restfulService.initIndex(indexName,typeName,shardNum,replicNum,builder);
            System.out.println(isSuccess);
            if(isSuccess){
                System.out.println("索引初始化成功.索引名: userdoc,类型名: file,分片数: 5,副本数: 1");
                Resource resource=new ClassPathResource("files");
                ObjectMapper objectMapper=new ObjectMapper();

                File fileDir=resource.getFile();
                ArrayList<String> fileList=new ArrayList<>();
                if(fileDir.exists()&&fileDir.isDirectory()){
                    File[] allFiles=fileDir.listFiles();
                    for(File f:allFiles){
                        Doc doc=tikaService.parserExtraction(f);
                        String json=objectMapper.writeValueAsString(doc);
                        fileList.add(json);
                    }
                }
                restfulService.indexDoc("userdoc","file",fileList);
            }
            else {
                System.out.println("索引初始化失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchKeyWords(){
        String[] searchFields={"title","filecontent"};
        System.out.println(restfulService.searchDocs("userdoc","中国科学院",searchFields,1,10));
    }

}
