package com.spmms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.spmms.dao.BuyCargoDao;
import com.spmms.pojo.BuyCargo;
import com.spmms.vo.PaginationVo;

@Repository("buyCargoDao")
public class BuyCargoDaoImpl implements BuyCargoDao {
 
	@Resource
	private SessionFactory sessionFactory;
	@Override
	public void save(BuyCargo buyCargo) {
		getSession().save(buyCargo);
		
	}
		private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
		@Override
		public PaginationVo<Object[]> getByPage(Integer pageNo,
				Integer pageSize, String startTime, String endTime,
				String model, String cargoName) {
			//得到起始页
			int startLine=(pageNo-1)*pageSize;
			StringBuilder str=new StringBuilder();
			StringBuilder str1=new StringBuilder();
			str.append("from BuyCargo  buy , Repository rep   where buy.barcode=rep.barcode ");
			str1.append("select count(*) from BuyCargo buy , Repository rep   where buy.barcode=rep.barcode ");
			List<String> mes=new ArrayList<String>();
			if(startTime!=null&&!"".equals(startTime)){
				str.append("and buy.buytime>? ");
				str1.append("and buy.buytime>? " );
				mes.add(startTime);
			}
			if(endTime!=null&&!"".equals(endTime)){
				str.append("and buy.buytime<? ");
				str1.append("and buy.buytime<? ");
				mes.add(endTime);
			}
			if(model!=null&&!"".equals(model)){
				str.append("and rep.model=? ");
				str1.append("and rep.model=? ");
				mes.add(model);
			}
			if(cargoName!=null&&!"".equals(cargoName)){
				str.append("and rep.name like ?");
				str1.append("and rep.name like ?");
				mes.add("%"+cargoName+"%");
				
			}
			
			Query query=getSession().createQuery(str.toString());
			
			Query query1=getSession().createQuery(str1.toString());
			for (int i=0;i<mes.size();i++) {
				query.setParameter(i, mes.get(i));
				query1.setParameter(i, mes.get(i));
			}
			List<Object[]> dataList=query.setFirstResult(startLine).setMaxResults(pageSize).list();
			
			Long total=(Long) query1.uniqueResult();
			PaginationVo<Object[]> paginationVO=new PaginationVo<Object[]>();
			paginationVO.setDataList(dataList);
			paginationVO.setTotal(total);
			
			
			return paginationVO;
		}
}
