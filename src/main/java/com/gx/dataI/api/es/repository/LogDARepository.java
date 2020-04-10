package com.gx.dataI.api.es.repository;

import com.gx.dataI.api.es.entity.LogDA;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogDARepository extends ElasticsearchRepository<LogDA,String> {
}
