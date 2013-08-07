package com.lkw.web.service;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  
import com.lkw.web.dao.UserMapper;
import com.lkw.web.model.User;

@Service
public class UserMapperImpl {

	@Autowired
	UserMapper userMapper;
	
	public User getUser(int id) {
		return userMapper.getUser(id);
	}

}
