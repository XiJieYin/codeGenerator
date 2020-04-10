package com.gx.dataI.api.es.repository;

import com.gx.dataI.api.es.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface UserRepository extends ElasticsearchRepository<User,String> {

    /**
     * 根据用户名来查询用户
     * @param userName 使用用户名
     * @return
     */
    Optional<User> findByUserName(String userName);

    /**
     * 按用户名和密码查询 用于登录
     * @param userName  登录用户名
     * @param password  登录密码
     * @return
     */
    Optional<User> findByUserNameAndPassword(String userName,String password);
}
