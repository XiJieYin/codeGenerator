package com.gx.dataI.api.es.repository;

import com.gx.dataI.api.es.entity.Log;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogRepository extends ElasticsearchRepository<Log,String> {

    /**
     * 根据用户名删除日志
     * @param userName 用户名
     */
    void deleteByUserName(String userName);

}
