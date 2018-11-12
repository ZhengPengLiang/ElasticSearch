package com.springbootlearn.damo.service;


import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IESRestfulService {

    RestHighLevelClient getRestClient();
    /**
     * 初始化索引
     *
     * @param indexName
     * @param typeName
     * @param shardNum
     * @param replicNum
     * @param builder
     * @return
     */
    Boolean initIndex(String indexName,
                      String typeName,
                      int shardNum,
                      int replicNum,
                      XContentBuilder builder);
    /**
     * 单个文本索引
     * @param indexName
     * @param typeName
     * @param jsonString
     * @return
     */
    boolean indexDoc(String indexName,
                     String typeName,
                     String id,
                     String jsonString);
    /**
     * 判断索引是否存在
     *
     * @param indexName
     * @return
     */
    boolean existIndex(String indexName);
    /**
     * 删除索引
     *
     * @param indexName
     * @return
     */
     boolean deleteIndex(String indexName);
    /**
     * 批量索引文档
     *
     * @param indexName
     * @param typeName
     * @param docList
     * @return
     */

     boolean indexDoc(String indexName,
                            String typeName,
                            List docList);
    /**
     * 搜索文档
     *
     * @param indics
     * @param keyword
     * @param fieldNames
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ArrayList<Map<String, Object>> searchDocs(String indics,
                                                     String keyword,
                                                     String[] fieldNames,
                                                     int pageNum,
                                                     int pageSize);
}
