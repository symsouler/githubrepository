package com.unicom.process.module.workflow.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eos.workflow.api.IBPSServiceClient;
import com.eos.workflow.api.IWFProcessInstManager;
import com.eos.workflow.api.IWFWorkItemManager;
import com.eos.workflow.data.WFProcessInst;
import com.eos.workflow.data.WFWorkItem;
import com.ibm.gbs.core.common.PageModel;
import com.ibm.gbs.core.exception.DBException;
import com.ibm.gbs.core.exception.WorkflowException;
import com.ibm.gbs.core.service.impl.BaseTaxServiceImpl;
import com.primeton.workflow.api.WFServiceException;
import com.unicom.process.module.workflow.dao.IProcessCheckDao;
import com.unicom.process.module.workflow.dao.IWorkItemCheckDao;
import com.unicom.process.module.workflow.service.IProcessCheckRService;
import com.unicom.process.module.workflow.util.BPSclientFactory;

public class ProcessCheckRServiceImpl extends BaseTaxServiceImpl implements
		IProcessCheckRService {

	private IProcessCheckDao processCheckDao;
	
	private IWorkItemCheckDao workItemCheckDao;
	public void setProcessCheckDao(IProcessCheckDao processCheckDao) {
		this.processCheckDao = processCheckDao;
	}



	public void setWorkItemCheckDao(IWorkItemCheckDao workItemCheckDao) {
		this.workItemCheckDao = workItemCheckDao;
	}



	@Override
	public void getProcessByParam(String profnsl_id, String comid) {
		StringBuilder sql=new StringBuilder();
		sql.append("select PROCESS_INSTANCE_ID from t_form_process where PROFSNL_ID="+profnsl_id);
		PageModel pageModel=new PageModel();
		processCheckDao.getProcess(sql.toString(),pageModel);
		
		
		
	}

	
	@Override
	public List<Map<String,Object>> getProcessByParam(String profnsl_id, String compid,PageModel pageModel) {
		StringBuilder sql=new StringBuilder();
		StringBuilder sql1=new StringBuilder();
		//StringBuilder sql2=new StringBuilder();
		StringBuilder sql3=new StringBuilder();
		WFWorkItem workItem=null;
		Integer processInstState=null;
		List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
		sql.append("select FORM_PROCESS_ID,PROFSNL_ID,COMP_ID, PROCESS_INSTANCE_ID from t_form_process where PROFSNL_ID='"+profnsl_id+"'");
		if (compid!=null&&!"".equals(compid)){
			sql.append(" and COMP_ID='"+compid+"'");
			
		}
		processCheckDao.getProcess(sql.toString(),pageModel);
		System.out.println(pageModel);
		//输入的表单批次有问题，在t_form_process表中查不到任何数据
		if(pageModel.getRecords().size()==0){
			return null;
		}
		//t_form_process表中出现同一表单批次多条数据的问题
		int processCount=pageModel.getRecords().size();
				if(processCount>1){
					for(int i=1;i<processCount;i++){
						Object[] objects= (Object[]) pageModel.getRecords().get(i);
						Map<String,Object> dataMap=new HashMap<String,Object>();
						dataMap.put("errorMsg", "该操作流程在流程表中存在重复信息,您可以删除当前流程以解决当前问题");
						dataMap.put("id",objects[0]);
						dataMap.put("instanceId", objects[3]);
						dataMap.put("operationState", 0);
						dataMap.put("compid", compid);
						dataList.add(dataMap);
					}
					pageModel.setRecords(dataList);
					return dataList;
				}
				
				
		Object[] objects= (Object[]) pageModel.getRecords().get(0);
		//获得流程实例id
		BigInteger instance_id=(BigInteger) objects[3];
		
		
		//Map<String,Object> dataMap=new HashMap<String,Object>();
		
		
		if(instance_id!=null){//判断流程表中获得流程实例id是否存在
			
		sql1.append(" select ID,PROCESS_INST_ID,WORKITEM_ID,WORKITEM_NAME,HANDLER_NAME,CURRENT_STATE from t_process_wiparticipant_record where PROCESS_INST_ID ='"+instance_id+"'");
		
		if (compid!=null&&!"".equals(compid)){
			sql1.append(" and COMP_ID='"+compid+"'");
		}
		PageModel newPageModel=new PageModel();
		
		//newPageModel中获得数据是代办记录表中的数据
		processCheckDao.getProcessWiparticipant(sql1.toString(), newPageModel);
		
		
		try {
			processInstState=BPSclientFactory.getInstance().getProcessInstManager().getProcessInstState(instance_id.longValue());
			
		} catch (WFServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WorkflowException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//sql3.append(" select workItemID from wfworkitem where processInstID='"+instance_id+"'");
		
		//workItemCheckDao.getWorkItem(sql3.toString(), pageModel);
		//判断bps中获得数据如果是空的
		if(processInstState==null){
			Map<String,Object> dataMap=new HashMap<String,Object>();
			dataMap.put("errorMsg", "该流程在bps中没有对应的数据，您可以删除当前流程信息以解决当前问题");
			dataMap.put("id", objects[0]);
			
			dataMap.put("compid", compid);
			dataMap.put("instanceId", objects[3]);
			//操作状态为0只能删除，为1只能 修改,2为不做任何操作
			dataMap.put("operationState", 0);
			dataList.add(dataMap);
			pageModel.setRecords(dataList);
			return dataList;
		}
		//判断bps中获得数据如果不是空的
		else{
			//sql2.append("select workItemID,workItemName,currentState from wfworkitem where processInstID='"+instance_id+"'");
			//processCheckDao.getWorkItem(sql2.toString(),pageModel);
			
			for(int i=0;i<newPageModel.getRecords().size();i++){
			Object[] object1s=(Object[]) newPageModel.getRecords().get(i);
			
			try {
				IBPSServiceClient bpsClient=BPSclientFactory.getInstance();
				 IWFWorkItemManager workItemManager=bpsClient.getWorkItemManager();
				 workItem= workItemManager.queryWorkItemDetail(Long.parseLong(object1s[2].toString()));
			
				 System.out.println(workItem);
				 
				 if(workItem!=null){
						//对workItem中的状态进行判断
						int status=workItem.getCurrentState();
						
						if(((BigDecimal) object1s[5]).intValue()==status){
							Map<String,Object> dataMap=new HashMap<String,Object>();
							dataMap.put("errorMsg", "无");
							dataMap.put("id",object1s[0]);
							dataMap.put("instanceId", object1s[1]);
							dataMap.put("workItemId",object1s[2]);
							dataMap.put("workItemName",object1s[3]);
							dataMap.put("handleName",object1s[4]);
							dataMap.put("currentState",object1s[5]);
							dataMap.put("operationState", 2);
							dataMap.put("compid", compid);
							dataList.add(dataMap);
						}
						else{
							Map<String,Object> dataMap=new HashMap<String,Object>();
							dataMap.put("errorMsg", "该工作项状态不正确，您可以修改当前转态以解决问题");
							dataMap.put("id",object1s[0]);
							dataMap.put("instanceId", object1s[1]);
							dataMap.put("workItemId",object1s[2]);
							dataMap.put("workItemName",object1s[3]);
							dataMap.put("handleName",object1s[4]);
							dataMap.put("currentState",object1s[5]);
							dataMap.put("operationState", 1);
							dataMap.put("editState", status);
							dataMap.put("compid", compid);
							dataList.add(dataMap);
						}
					}
			} catch (WFServiceException e) {
				//错误编号21000000表示workItem不存在
				if(e.getErrorNo()==21000000){
				Map<String,Object> dataMap=new HashMap<String,Object>();
				dataMap.put("errorMsg", "该工作项在bps中并不存在,您可以删除当前工作项以解决当前问题");
				dataMap.put("id",object1s[0]);
				dataMap.put("instanceId", object1s[1]);
				dataMap.put("workItemId",object1s[2]);
				dataMap.put("workItemName",object1s[3]);
				dataMap.put("handleName",object1s[4]);
				dataMap.put("currentState",object1s[5]);
				dataMap.put("operationState", 0);
				dataMap.put("compid", compid);
				dataList.add(dataMap);
				}else{
					e.printStackTrace();
				}
				} catch (WorkflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//String sqlString="select currentState from wfworkitem where processInstID='"+instance_id+"' and workItemID='"+object1s[2]+"'";
			//pageModel.setRecords(null);
			//根据流程实例id和workitemid去bps的工作项表中查询,用另外一个dao
			//workItemCheckDao.getWorkItem(sqlString,pageModel);
			/*if(pageModel.getRecords().size()==0){
				Map<String,Object> dataMap=new HashMap<String,Object>();
				dataMap.put("errorMsg", "该工作项在bps中并不存在,您可以删除当前工作项以解决当前问题");
				dataMap.put("id",object1s[0]);
				dataMap.put("instanceId", object1s[1]);
				dataMap.put("workItemId",object1s[2]);
				dataMap.put("workItemName",object1s[3]);
				dataMap.put("handleName",object1s[4]);
				dataMap.put("currentState",object1s[5]);
				dataMap.put("operationState", 0);
				dataMap.put("compid", compid);
				dataList.add(dataMap);
			}
			else{
				BigDecimal currentState=(BigDecimal) pageModel.getRecords().get(0);
				if(currentState.equals((BigDecimal)object1s[5])){
					Map<String,Object> dataMap=new HashMap<String,Object>();
					dataMap.put("errorMsg", "无");
					dataMap.put("id",object1s[0]);
					dataMap.put("instanceId", object1s[1]);
					dataMap.put("workItemId",object1s[2]);
					dataMap.put("workItemName",object1s[3]);
					dataMap.put("handleName",object1s[4]);
					dataMap.put("currentState",object1s[5]);
					dataMap.put("operationState", 2);
					dataMap.put("compid", compid);
					dataList.add(dataMap);
					
				}
				else{
					Map<String,Object> dataMap=new HashMap<String,Object>();
					dataMap.put("errorMsg", "该工作项状态不正确，您可以修改当前转态以解决问题");
					dataMap.put("id",object1s[0]);
					dataMap.put("instanceId", object1s[1]);
					dataMap.put("workItemId",object1s[2]);
					dataMap.put("workItemName",object1s[3]);
					dataMap.put("handleName",object1s[4]);
					dataMap.put("currentState",object1s[5]);
					dataMap.put("operationState", 1);
					dataMap.put("editState", currentState);
					dataMap.put("compid", compid);
					dataList.add(dataMap);
				}
			}*/
			
		}
			pageModel.setRecords(dataList);
			return dataList;
			}
		}
		else{
			return null;
		}
		
	}

	@Override
	public void updateProcess(String id, String editState) throws DBException{
		StringBuilder sql=new StringBuilder();
		sql.append("update t_process_wiparticipant_record set CURRENT_STATE='"+editState+"' ");
		sql.append(" where ID='"+id+"'");
		try {
			processCheckDao.updateWiparticipant(sql.toString());
		} catch (DBException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteWorkItem(String id) throws DBException {
		StringBuilder sql=new StringBuilder();
		sql.append("delete from t_process_wiparticipant_record where ID='"+id+"'");
		processCheckDao.deleteWorkItem(sql.toString());
		
	}



	@Override
	public PageModel getAllTaxOfficialList(PageModel pm,
			String taxOfficialCode, String taxOfficialName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public int getWfworkitem(int processInstId) throws Exception{
		IBPSServiceClient bpsClient=BPSclientFactory.getInstance();
		 IWFProcessInstManager processInstManager=bpsClient.getProcessInstManager();
		 int state=processInstManager.getProcessInstState(processInstId);
		 return state;
		
	}

}
