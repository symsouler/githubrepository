package com.spmms.dao;

import com.spmms.vo.PaginationVo;

public interface SellCargoDao {

	PaginationVo<Object[]> getByPage(PaginationVo<Object[]> paginationVo,
			String startTime, String endTime, String model);

}
