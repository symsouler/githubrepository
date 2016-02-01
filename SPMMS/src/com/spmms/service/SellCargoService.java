package com.spmms.service;

import com.spmms.vo.PaginationVo;

public interface SellCargoService {

	PaginationVo<Object[]> getByPage(PaginationVo<Object[]> paginationVo,
			String startTime, String endTime, String model);

}
