package com.omall.dao;

import com.omall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUserName(String userName);

    User selectLogin(@Param("username") String userName, @Param("password") String password);

    int checkEmail(String email);

    String selectQuestionByUserName(String username);

    int checkAnswer(@Param("username")String username, @Param("question")String question,@Param("answer")String answer);

    int updatePasswordByUsername(@Param("username")String username,  @Param("passwordNew") String passwordNew);

    int checkPassword(@Param("password")String password, @Param("userId")Integer userId);

    int checkEmailByUserId(@Param("email")String email,  @Param("userId")Integer id);
}