package com.gx.dataI.api.service;

import com.gx.dataI.api.es.entity.User;

public interface ILoginService {
    User checkUser(String userName, String password);
}
