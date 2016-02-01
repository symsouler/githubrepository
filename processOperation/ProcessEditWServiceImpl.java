package com.unicom.process.module.workflow.service.impl;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

import com.ibm.gbs.core.dao.IBaseDao;
import com.ibm.gbs.core.exception.DBException;
import com.ibm.gbs.core.service.impl.BaseTaxServiceImpl;
import com.unicom.common.service.pojo.RouterPojo;
import com.unicom.process.module.workflow.dao.IProcessCheckDao;
import com.unicom.process.module.workflow.dao.IWorkItemCheckDao;
import com.unicom.process.module.workflow.service.IProcessEditWService;

public class ProcessEditWServiceImpl extends BaseTaxServiceImpl implements IProcessEditWService {

	
	private IProcessCheckDao processCheckDao;
	
	public void setProcessCheckDao(IProcessCheckDao processCheckDao) {
		this.processCheckDao = processCheckDao;
	}

	@Override
	public void updateProcess(String id, String editState, String compid) throws DBException{
		StringBuilder sql=new StringBuilder();
		sql.append("update t_process_wiparticipant_record set CURRENT_STATE='"+editState+"' ");
		if(id!=null&&!"".equals(id)){
		sql.append(" where ID='"+id+"'");
		}
		if(compid!=null&&"".equals(id)){
			sql.append(" and COMP_ID='"+compid+"'");
			
		}
		try {
			processCheckDao.updateWiparticipant(sql.toString());
		} catch (DBException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteWorkItem(String id, String compid) throws DBException {
		StringBuilder sql=new StringBuilder();
		sql.append("delete from t_process_wiparticipant_record where ID='"+id+"'");
		if(compid!=null&&"".equals(id)){
			sql.append(" and COMP_ID='"+compid+"'");
			
		}
		processCheckDao.deleteWorkItem(sql.toString());
		
	}

	@Override
	public void deleteProcess(String id, String compid) throws DBException {
		StringBuilder sql=new StringBuilder();
		sql.append("delete from t_form_process where T_FORM_PROCESS_ID='"+id+"'");
		
		if(compid!=null&&"".equals(id)){
			sql.append(" and COMP_ID='"+compid+"'");
			
		}
	}

	

}
