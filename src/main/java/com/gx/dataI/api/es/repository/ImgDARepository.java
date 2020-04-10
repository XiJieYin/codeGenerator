package com.gx.dataI.api.es.repository;

import com.gx.dataI.api.es.entity.ImgDA;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ImgDARepository extends ElasticsearchRepository<ImgDA,String> {
}
