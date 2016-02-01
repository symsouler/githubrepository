package com.spmms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spmms.dao.LoginDao;
import com.spmms.pojo.User;
import com.spmms.service.LoginService;

@Service(value = "loginService")
public class LoginServiceImpl implements LoginService {

	@Resource(name="loginDao")
	private LoginDao loginDao;

	@Override
	public User getUserBycodeAndPassword(User user) {
		
		return loginDao.getUserBycodeAndPassword(user.getLogincode(),user.getPassword());
	}
	
	
}
