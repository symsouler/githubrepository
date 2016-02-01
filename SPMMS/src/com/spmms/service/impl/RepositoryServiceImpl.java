package com.spmms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spmms.dao.RepositoryDao;
import com.spmms.pojo.Repository;
import com.spmms.service.RepositoryService;
import com.spmms.vo.PaginationVo;

@Service("repositoryService")
public class RepositoryServiceImpl implements RepositoryService {

	@Resource
	private RepositoryDao repositoryDao;
	@Override
	public PaginationVo<Repository> getByPage(
			PaginationVo<Repository> paginationVo) {
		
		return repositoryDao.getByPage(paginationVo);
	}

}
