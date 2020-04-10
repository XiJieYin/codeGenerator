package com.gx.dataI.api.service.impl;

import com.gx.dataI.api.es.entity.User;
import com.gx.dataI.api.es.repository.UserRepository;
import com.gx.dataI.api.service.ILoginService;
import com.gx.dataI.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    UserRepository repository;

    @Override
    public User checkUser(String userName, String password) {
        Optional<User> user = repository.findByUserNameAndPassword(userName, StringUtil.encodeMD5(password));
        return user.isPresent()?user.get():null;
    }
}
