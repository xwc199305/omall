package com.omall.service;

import com.omall.common.ServerResponse;
import com.omall.pojo.User;

/**
 * Created by wencxu on 2018/3/15.
 */
public interface IUserService {

    ServerResponse<User> login(String usename, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str,String type);

    ServerResponse<String> selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username, String question, String answer);

    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInformation(Integer id);
}
