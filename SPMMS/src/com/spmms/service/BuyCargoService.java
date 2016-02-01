package com.spmms.service;

import com.spmms.pojo.BuyCargo;
import com.spmms.pojo.Repository;
import com.spmms.pojo.SellCargo;
import com.spmms.vo.PaginationVo;

public interface BuyCargoService {

	

	void save(BuyCargo buyCargo, SellCargo sellCargo, Repository repository);

	PaginationVo<Object[]> getByPage(Integer pageNo, Integer pageSize,
			String startTime, String endTime, String model, String cargoName);

}
