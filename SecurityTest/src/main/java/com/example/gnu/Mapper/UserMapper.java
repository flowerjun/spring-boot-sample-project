package com.example.gnu.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
	String selectUserById(@Param("id") String id, @Param("password") String password);
	String findByUsername(@Param("username") String username);
}
