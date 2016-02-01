package com.spmms.dao.impl;

import javax.annotation.Resource;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.spmms.dao.LoginDao;
import com.spmms.pojo.User;

@Repository(value="loginDao")
public class LoginDaoImpl implements LoginDao {

	@Resource
	private SessionFactory sessionFactory;
	
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public User getUserBycodeAndPassword(String logincode, String password) {
		User user=(User) getSession().createQuery("from User user where user.logincode=? and user.password=?")
		.setParameter(0, logincode).setParameter(1,password).uniqueResult();
		return user;
	}

}
