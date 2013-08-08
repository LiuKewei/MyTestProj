package com.lkw.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lkw.web.model.User;
import com.lkw.web.service.UserMapperImpl;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
@Scope("request")
@Controller("loginAction")

public class LoginAction extends ActionSupport {
    private String idl;

	public String getIdl() {
		return idl;
	}

	public void setIdl(String idl) {
		this.idl = idl;
	}

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Autowired
	UserMapperImpl userMapperImpl;

	@Override
	public String execute() throws Exception {
		System.out.println(this.idl);
		user = userMapperImpl.getUser(Integer.parseInt(this.idl));
		System.out.println(user.getName());
		System.out.println(user.getId());
		return "ok";
	}
	
	
	
	
}
