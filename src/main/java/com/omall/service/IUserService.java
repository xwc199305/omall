package com.omall.service;

import com.omall.common.ServerResponse;
import com.omall.pojo.User;
import org.springframework.stereotype.Service;

/**
 * Created by wencxu on 2018/3/15.
 */
public interface IUserService {

    ServerResponse<User> login(String usename, String password);

    ServerResponse<User> register(User user);

    ServerResponse<String> checkValid(String str,String type);

    ServerResponse<String> selectQuestion(String username);
}
