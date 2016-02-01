package com.spmms.dao;

import com.spmms.pojo.Repository;
import com.spmms.vo.PaginationVo;

public interface RepositoryDao {

	Repository getByBarcode(String barcode);

	void save(Repository repository);

	void update(Repository newRep);

	PaginationVo<Repository> getByPage(PaginationVo<Repository> paginationVo);

}
