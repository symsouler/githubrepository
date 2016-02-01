package com.spmms.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.spmms.dao.SellCargoDao;
import com.spmms.vo.PaginationVo;

@Repository("sellCargoDao")
public class SellCargoDaoImpl implements SellCargoDao {

	private SessionFactory sessionFactory;
	@Override
	public PaginationVo<Object[]> getByPage(
			PaginationVo<Object[]> paginationVo, String startTime,
			String endTime, String model) {
		
		
		
		return null;
	}

	
}
