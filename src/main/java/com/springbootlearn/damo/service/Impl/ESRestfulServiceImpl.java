package com.springbootlearn.damo.service.Impl;

import com.springbootlearn.damo.service.IESRestfulService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class ESRestfulServiceImpl implements IESRestfulService {
    private  Logger logger= LoggerFactory.getLogger(getClass());

    //elasticsearch通过restful连接的API
    @Override
    public RestHighLevelClient getRestClient() {
        RestHighLevelClient restHighLevelClient=new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http")));
        return restHighLevelClient;
    }

    @Override
    public Boolean initIndex(String indexName, String typeName, int shardNum, int replicNum, XContentBuilder builder) {
        //创建索引
        RestHighLevelClient restHighLevelClient=getRestClient();
        CreateIndexRequest request=new CreateIndexRequest(indexName);

        //设置分片和副本的个数
        request.settings(Settings.builder().put("index.number_of_shards",shardNum).put("index.number_of_replicas",replicNum));

        request.mapping(typeName,builder);
        CreateIndexResponse createIndexResponse=null;
        try {
            createIndexResponse=restHighLevelClient.indices().create(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return createIndexResponse.isAcknowledged();
    }

    @Override
    public boolean indexDoc(String indexName, String typeName, String id, String jsonString) {
        return false;
    }

    @Override
    public boolean existIndex(String indexName) {
        return false;
    }

    @Override
    public boolean deleteIndex(String indexName) {
        return false;
    }

    @Override
    public boolean indexDoc(String indexName, String typeName, List docList) {
        RestHighLevelClient client=getRestClient();
        BulkRequest bulkRequest=new BulkRequest();
        Iterator<String> it=docList.iterator();
        while(it.hasNext()){
            String jsonString=it.next();
            IndexRequest indexRequest=new IndexRequest(indexName,typeName).source(jsonString, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        try{
            BulkResponse bulkResponse=client.bulk(bulkRequest);
            if(bulkResponse.hasFailures()){
                logger.error("批量索引失败");
                return false;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public ArrayList<Map<String, Object>> searchDocs(String indics, String keyword, String[] fieldNames, int pageNum, int pageSize) {
        return null;
    }
}
