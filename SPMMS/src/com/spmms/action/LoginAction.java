package com.spmms.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.spmms.pojo.User;
import com.spmms.service.LoginService;

public class LoginAction extends BaseAction{
	
	@Resource
	private LoginService loginService;
	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String login() throws Exception {
		//从数据库中查找是否有这个用户
		User user1=loginService.getUserBycodeAndPassword(user);
		if(loginService.getUserBycodeAndPassword(user)!=null){
			//获取sessison
			HttpSession session=ServletActionContext.getRequest().getSession();
			session.setAttribute("loginUser", user1);
			return "success";	
		}
		else{
			return "login";
		}

	}
	public String logout() throws Exception{
		session.invalidate();
		
		return "login";
	}
}
