package com.my_oa.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my_oa.entities.Employee;
import com.my_oa.orm.Page;

@Repository
public class EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public Criteria getCriteria(){
		return this.getSession().createCriteria(Employee.class);
	}
	
	public Page<Employee> getPage(Page<Employee> page){
		
		//1、查询总的记录数
		int totalElements = this.getTotalElements();
		
		
		//2、为Page的totalElements属性赋值
		page.setTotalElements(totalElements);
		
		//3、查询当前页面的content
		
		List<Employee> content = getContent(page);
		
		//4、为content属性赋值
		page.setContent(content);
		
		//5、返回Page对象
		return page;
	}
	
	
	
	private List<Employee> getContent(Page<Employee> page) {
		
		int firstResult = (page.getPageNo()-1) * page.getPageSize();
		int maxResults = page.getPageSize();
		
		Criteria criteria = getCriteria().setFirstResult(firstResult).setMaxResults(maxResults);
		
		return criteria.list();
	}

/**
 * 
 * @return 返回总记录数
 */
	private int getTotalElements() {
		
		Criteria criteria = getCriteria();
		String idName = getIdName();
		
		Projection projection = Projections.count(idName);
		
		criteria.setProjection(projection);
		
		return (int)((long)criteria.uniqueResult());
	}


	private String getIdName() {
		ClassMetadata cmd = this.sessionFactory.getClassMetadata(Employee.class);
		return cmd.getIdentifierPropertyName();
	}

	public Employee getBy(String propertyName,String propertyVal){
		Criteria criteria = getCriteria();
		
		//调用Restrictions 工厂方法来获取 criterion 对象
		Criterion criterion = Restrictions.eq(propertyName, propertyVal);
		//添加查询对象
		criteria.add(criterion);
		//返回唯一结果
		return (Employee)criteria.uniqueResult();
	}
	
	
}
