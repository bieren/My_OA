package com.my_oa.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.my_oa.entities.Employee;
import com.my_oa.orm.Page;
import com.my_oa.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@Scope("prototype")
@Controller
public class EmployeeAction extends ActionSupport
		implements RequestAware, SessionAware, ModelDriven<Employee>, Preparable {


	private static final long serialVersionUID = 1L;

	@Autowired
	private EmployeeService employeeService;
	
	private Page<Employee> page;
	
	public Page<Employee> getPage() {
		return page;
	}

	public void setPage(Page<Employee> page) {
		this.page = page;
	}

	public void prepareLogin() {

		model=new Employee();
	}
	public void prepareList() {

		page=new Page<>();
	}

	public String list(){
		
		this.page = this.employeeService.getPage(page);
		return "list";
	}
	
	public String login() {
		//System.out.println("EmployeeAction#login");
		
		String loginName = model.getLoginName();
		
		String password = model.getPassword();
		
		Employee employee = employeeService.login(loginName, password);
		
		this.sessionMap.put("employee", employee);
		
		return SUCCESS;
	}

	@Override
	public void prepare() throws Exception {}

	private Employee model;

	@Override
	public Employee getModel() {
		return model;
	}

	
	private Map<String, Object> sessionMap=null;
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.sessionMap=arg0;
	}

	
	private Map<String, Object> requestMap=null;
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.requestMap=arg0;
	}

}
