package com.spmms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spmms.dao.BuyCargoDao;
import com.spmms.dao.RepositoryDao;
import com.spmms.pojo.BuyCargo;
import com.spmms.pojo.Repository;
import com.spmms.pojo.SellCargo;
import com.spmms.service.BuyCargoService;
import com.spmms.vo.PaginationVo;

@Service("buyCargoService")
public class BuyCargoServiceImpl implements BuyCargoService {

	@Resource
	private BuyCargoDao buyCargoDao;
	@Resource
	private RepositoryDao repositoryDao;
	@Override
	public void save(BuyCargo buyCargo,SellCargo sellCargo, Repository repository) {
		
		String barcode=buyCargo.getBarcode();
		Repository newRep= repositoryDao.getByBarcode(barcode);
		if(newRep==null){
			//String i="13853321061";
			repository.setBarcode(buyCargo.getBarcode());
			repository.setRcount(buyCargo.getCount());
			repositoryDao.save(repository);
		}
		else{
			newRep.setRcount(newRep.getRcount()+buyCargo.getCount());
			repositoryDao.update(newRep);
		}
		
		buyCargoDao.save(buyCargo);
	}
	@Override
	public PaginationVo<Object[]> getByPage(Integer pageNo, Integer pageSize,
			String startTime, String endTime, String model, String cargoName) {
		return buyCargoDao.getByPage(pageNo, pageSize,
			startTime, endTime, model, cargoName);
		
	}

}
