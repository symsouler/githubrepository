package com.spmms.service;

import com.spmms.pojo.Repository;
import com.spmms.vo.PaginationVo;

public interface RepositoryService {

	PaginationVo<Repository> getByPage(PaginationVo<Repository> paginationVo);

}
