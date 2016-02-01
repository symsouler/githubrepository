package com.spmms.action;

import javax.annotation.Resource;

import com.spmms.service.SellCargoService;
import com.spmms.vo.PaginationVo;

public class SellCargoAction extends BaseAction {

	@Resource
	private SellCargoService sellCargoService;
	private PaginationVo<Object[]> paginationVo;
	private String startTime;
	private String endTime;
	private String model;
	
	
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public PaginationVo<Object[]> getPaginationVo(){
		return paginationVo;
	}

	public void setPaginationVo(PaginationVo<Object[]> paginationVo) {
		this.paginationVo = paginationVo;
	}

	public String index() throws Exception{
		return SUCCESS;
	}
	
	public String add() throws Exception{
		return SUCCESS;
	}
	
	//保存数据操作
	public String save() throws Exception{
		
		return null;
	}
	
	//分页处理
	public String getByPage() throws Exception{
		paginationVo=sellCargoService.getByPage(paginationVo,startTime,endTime,model);
		
		return null;
	}
	
}
