package com.spmms.dao;

import com.spmms.pojo.BuyCargo;
import com.spmms.vo.PaginationVo;

public interface BuyCargoDao {

	void save(BuyCargo buyCargo);

	PaginationVo<Object[]> getByPage(Integer pageNo, Integer pageSize,
			String startTime, String endTime, String model, String cargoName);

}
