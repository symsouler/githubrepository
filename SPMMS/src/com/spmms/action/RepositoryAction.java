package com.spmms.action;

import javax.annotation.Resource;

import com.spmms.pojo.Repository;
import com.spmms.service.RepositoryService;
import com.spmms.vo.PaginationVo;

public class RepositoryAction extends BaseAction{

	private PaginationVo<Repository> paginationVo;
	@Resource
	private RepositoryService repositoryService;
	public PaginationVo<Repository> getPaginationVo() {
		return paginationVo;
	}
	public void setPaginationVo(PaginationVo<Repository> paginationVo) {
		this.paginationVo = paginationVo;
	}
	public String index() throws Exception{
		
		return "success";
	}
	public String add() throws Exception{
		
		return "success";
	}
	
	//分页处理
	public String getByPage() throws Exception{
		paginationVo=repositoryService.getByPage(paginationVo);
		return jsonOut(paginationVo);
	}
	
	//导出信息处理
	public String exportExcel() throws Exception{
		return null;
	}
}
