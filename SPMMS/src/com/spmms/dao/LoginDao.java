package com.spmms.dao;

import com.spmms.pojo.User;

public interface LoginDao {

	User getUserBycodeAndPassword(String logincode, String password);

}
