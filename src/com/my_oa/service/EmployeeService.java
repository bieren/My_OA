package com.my_oa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my_oa.dao.EmployeeDao;
import com.my_oa.entities.Employee;
import com.my_oa.exception.AccountUnabledException;
import com.my_oa.exception.LoginNotFoundException;
import com.my_oa.exception.PasswordNotMatchException;
import com.my_oa.orm.Page;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	
	
	
	@Transactional(readOnly=true)
	public Page<Employee> getPage(Page<Employee> page){
		
		return employeeDao.getPage(page);
	}
	
	
	//完成登录操作
	@Transactional
	public Employee login(String loginName,String password){
		//1、查询loginName对应的Employee是否存在
		Employee employee = employeeDao.getBy("loginName", loginName);
		
		if(employee == null){
			throw new LoginNotFoundException();
		}
		
		//2、若存在，则判断该账户是否可用
		if(employee.getEnabled() != 1){
			throw new AccountUnabledException();
		}
		
		//3、若该账户可用，则比对密码
		if(!password.equals(employee.getPassword())){
			throw new PasswordNotMatchException();
		}
		
		//4、若密码匹配，则登录次数+1
		employee.setVisitedTimes(employee.getVisitedTimes() + 1);
		
		
		
		//5、返回Employee对象
		
		return employee;
	}
	
	
}
