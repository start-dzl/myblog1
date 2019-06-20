package com.dzl.myblog.mapper;

import com.dzl.myblog.entity.User;
import com.dzl.myblog.entity.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user where phone=#{phone}")
    @Results({
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "phone", property = "roles", javaType = List.class, many = @Many(select = "com.dzl.myblog.mapper.UserMapper.getRoleNameByPhone")),

    })
    User getUsernameAndRolesByPhone(@Param("phone") String phone);


    @Select("select r.name from user u LEFT JOIN user_role sru on u.id= sru.User_id LEFT JOIN role r on sru.Role_id=r.id where phone=#{phone}")
    Role getRoleNameByPhone(String phone);

    @Select("select * from user where phone=#{phone}")
    User SelectUserByPhone(@Param("phone") String Phone);
}
