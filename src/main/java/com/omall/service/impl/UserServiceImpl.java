package com.omall.service.impl;

import com.omall.common.Const;
import com.omall.common.ServerResponse;
import com.omall.dao.UserMapper;
import com.omall.pojo.User;
import com.omall.service.IUserService;

import com.omall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by wencxu on 2018/3/15.
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String userName, String password) {
        int resultCount = userMapper.checkUserName(userName);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        //todo 密码登录MD5
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(userName,md5Password);
        if(user == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("用户登录成功",user);
    }

    @Override
    public ServerResponse<User> register(User user) {
        int resultCount = userMapper.checkUserName(user.getUsername());
        if(resultCount > 0){
            return ServerResponse.createByErrorMessage("用户名已存在");
        }

        resultCount = userMapper.checkEmail(user.getEmail());
        if(resultCount > 0){
            return ServerResponse.createByErrorMessage("邮箱已存在");
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

        resultCount = userMapper.insert(user);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }

        return ServerResponse.createBySuccessMessage("注册成功");
    }

    public ServerResponse<User> checkValid(String str,String type){
        if(StringUtils.isNotBlank(type)){
            //开始校验
            if(Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUserName(str);
                if(resultCount > 0){
                    return ServerResponse.createByErrorMessage("该用户名已存在");
                }
            }
            if(Const.EMAIL.equals(type)){
                int resultCount = userMapper.checkEmail(str);
                if(resultCount > 0){
                    return ServerResponse.createByErrorMessage("该邮件已存在");
                }
            }
        }else{
            ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }
}
