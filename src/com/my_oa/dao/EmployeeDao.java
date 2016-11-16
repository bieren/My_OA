package com.my_oa.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my_oa.entities.Employee;

@Repository
public class EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public Employee getBy(String propertyName,String propertyVal){
		Criteria criteria = getSession().createCriteria(Employee.class);
		
		//调用Restrictions 工厂方法来获取 criterion 对象
		Criterion criterion = Restrictions.eq(propertyName, propertyVal);
		//添加查询对象
		criteria.add(criterion);
		//返回唯一结果
		return (Employee)criteria.uniqueResult();
	}
	
	
}
