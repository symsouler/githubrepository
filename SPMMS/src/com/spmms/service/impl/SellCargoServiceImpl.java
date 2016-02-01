package com.spmms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spmms.dao.SellCargoDao;
import com.spmms.service.SellCargoService;
import com.spmms.vo.PaginationVo;

@Service("sellCargoService")
public class SellCargoServiceImpl implements SellCargoService {
	@Resource
	private SellCargoDao sellCargoDao;
	@Override
	public PaginationVo<Object[]> getByPage(
			PaginationVo<Object[]> paginationVo, String startTime,
			String endTime, String model) {
		
		return sellCargoDao.getByPage(paginationVo,startTime,endTime,model);
	}

}
