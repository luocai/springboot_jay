package com.cai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cai.dao.UserMapper;
import com.cai.pojo.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	

	public void add(User user) throws Exception{
		
		userMapper.add(user);
	}


	public User findByName(String name) throws Exception{
		
		return userMapper.findByName(name);
	}

}
