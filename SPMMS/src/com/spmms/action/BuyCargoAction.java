package com.spmms.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.spmms.pojo.BuyCargo;
import com.spmms.pojo.Repository;
import com.spmms.pojo.SellCargo;
import com.spmms.service.BuyCargoService;
import com.spmms.vo.PaginationVo;

public class BuyCargoAction extends BaseAction{
	//设置属性驱动，并提供setter方法从页面中获取数据
	private String startTime;
	private String endTime;
	private String cargoName;
	private String model;
	//设置域驱动，并提供getter和setter方法获取数据
	private BuyCargo buyCargo;
	private SellCargo sellCargo;
	private Repository repository;
	private PaginationVo<Object[]> paginationVO; 
	@Resource
	private BuyCargoService buyCargoService;
	
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public BuyCargo getBuyCargo() {
		return buyCargo;
	}
	public void setBuyCargo(BuyCargo buyCargo) {
		this.buyCargo = buyCargo;
	}
	public SellCargo getSellCargo() {
		return sellCargo;
	}
	public void setSellCargo(SellCargo sellCargo) {
		this.sellCargo = sellCargo;
	}
	public Repository getRepository() {
		return repository;
	}
	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	
	public PaginationVo<Object[]> getPaginationVO() {
		return paginationVO;
	}
	public void setPaginationVO(PaginationVo<Object[]> paginationVO) {
		this.paginationVO = paginationVO;
	}
	public String getByPage() throws Exception{
		
		Map<String,Object> jsonMap=new HashMap<String,Object>();
		try {
			paginationVO=buyCargoService.getByPage(paginationVO.getPageNo(),paginationVO.getPageSize(),
					startTime,endTime,model,cargoName);
			//Map<String,Object> jsonMap=new HashMap<String,Object>();
		List<Object[]> daa =paginationVO.getDataList();
		List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
		//dao层中获取到的List中储存的是object数组.
		for (Object[] objects : daa) {
			Map<String,Object> dataMap=new HashMap<String,Object>();
			dataMap.put("id", ((BuyCargo) objects[0]).getId());
			dataMap.put("barcode", ((BuyCargo) objects[0]).getBarcode());
			dataMap.put("buyprice", ((BuyCargo) objects[0]).getBuyprice());
			dataMap.put("buytime", ((BuyCargo) objects[0]).getBuytime());
			dataMap.put("count", ((BuyCargo) objects[0]).getCount());
			dataMap.put("name", ((Repository) objects[1]).getName());
			dataList.add(dataMap);
		}
		
		
		jsonMap.put("dataList",dataList);
		jsonMap.put("total", paginationVO.getTotal());
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	public String save() throws Exception{
		Map<String,Object> jsonMap=new HashMap<String,Object>();
		try {
			buyCargoService.save(buyCargo,sellCargo,repository);
			jsonMap.put("success",true);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
		
	}
	public String add() throws Exception{
		return "success";
	}
	public String index() throws Exception{
		return "success";
	}
	
}
