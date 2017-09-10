package com.example.gnu.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.gnu.DAO.UserDAO;
import com.example.gnu.DTO.MyUser;

@Service
public class LoginService implements UserDetailsService {
	private final Logger LOG = LogManager.getLogger();

	@Autowired
	UserDAO userDao;
	
	@Autowired
	MyUser user;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		user = userDao.findUserByUsername(arg0);
		if (null == user) {
			LOG.warn("user {} is not exist", arg0);
			throw new UsernameNotFoundException("User not found");
		} else {
			String authority = userDao.findAuthorityByUsername(arg0);
			user.addAuthorities(authority);
			return user;
		}
	}
	
	public MyUser getCurrentUser(){
		return user;
	}
}
