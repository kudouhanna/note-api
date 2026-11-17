package com.example.noteapi.mapper;

import com.example.noteapi.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT user_id, username, password, created_at FROM users WHERE username = #{username}")
    User selectUserByUsername(String username);

    @Insert("INSERT INTO users(username, password, created_at) VALUES (#{username}, #{password}, CURRENT_TIMESTAMP)")
    void insertUser(String username, String password);
}
