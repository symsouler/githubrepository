package com.spmms.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("base-package")
public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected ServletContext application;
	
	
	
	/**
	 * 向客户端响应xml数据
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public String xmlOut(String xml)throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(xml);
		return null;
	}
	/**
	 * 通过jackson插件，将java对象转换为json字符串并响应给客户端
	 * @return
	 * @throws Exception
	 */
	public String jsonOut(Object jsonObject)throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(jsonObject);
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().print(jsonString);
		return null;
	}
	
	/**
	 * 向客户端响应html数据
	 * @param html
	 * @return
	 * @throws Exception
	 */
	public String htmlOut(String html)throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(html);
		return null;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
		this.application = session.getServletContext();
		
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}

