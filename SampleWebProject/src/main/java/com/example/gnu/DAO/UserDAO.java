package com.example.gnu.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gnu.DTO.MyUser;

@Mapper
@Repository
public interface UserDAO {
	MyUser findUserByUsername(@RequestParam("username") String username);
	String findAuthorityByUsername(@RequestParam("username") String username);
}
