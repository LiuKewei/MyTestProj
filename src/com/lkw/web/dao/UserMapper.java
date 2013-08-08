package com.lkw.web.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lkw.web.model.User;

@Scope("singleton")

@Service ("userMapper")
public interface UserMapper {
	public User getUser(int id);
}
