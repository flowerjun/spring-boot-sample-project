package com.example.gnu.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.gnu.Mapper.UserMapper;

@Component
public class UserDAO {
	@Autowired
	SqlSession sqlSession;
	@Autowired
	UserMapper userMapper;
	
	public String selectUserById(String id, String password){
		return userMapper.selectUserById(id, password);
	}

}
