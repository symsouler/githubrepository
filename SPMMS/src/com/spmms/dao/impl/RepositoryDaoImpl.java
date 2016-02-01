package com.spmms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.spmms.dao.RepositoryDao;
import com.spmms.pojo.Repository;
import com.spmms.vo.PaginationVo;


@org.springframework.stereotype.Repository("repositoryDao")
public class RepositoryDaoImpl implements RepositoryDao {
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public Repository getByBarcode(String barcode) {
		Repository req= (Repository)getSession().get(Repository.class, barcode);
		return req;
	}
	@Override
	public void save(Repository repository) {
			getSession().save(repository);
		
	}
	@Override
	public void update(Repository newRep) {
		getSession().update(newRep);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public PaginationVo<Repository> getByPage(
			PaginationVo<Repository> paginationVo) {
		//起始行
		int startLine=(paginationVo.getPageNo()-1)*paginationVo.getPageSize();
		//得到分页数据的hql
		StringBuilder hql=new StringBuilder();
		//得到数据量total的hql
		StringBuilder hql1=new StringBuilder();
		hql.append("from Repository rep where 1=1 ");
		hql1.append("select count(*) from Repository rep where 1=1");
		List<String> repList=new ArrayList<String>();
		if(paginationVo.getName()!=null&&!"".equals(paginationVo.getName())){
			hql.append(" and rep.name like ?");
			repList.add("%"+paginationVo.getName()+"%");
		}
		Query query=getSession().createQuery(hql.toString());
		Query query1=getSession().createQuery(hql1.toString());
	
		
		for(int i=0;i<repList.size();i++){
			query.setParameter(i, repList.get(i));
			query1.setParameter(i, repList.get(i));
		}
		
		
		List<Repository> repositories= query.setFirstResult(startLine)
		 .setMaxResults(paginationVo.getPageSize()).list();
		
		Long total=(Long)query1.uniqueResult();
		
		paginationVo.setDataList(repositories);
		paginationVo.setTotal(total);
		return paginationVo;
	}

	
}
