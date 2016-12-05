package com.india.sayantan.main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.india.sayantan.jdbc.EmpDaoImpl;
import com.india.sayantan.model.Triangle;



public class InjectJpaDatabase {

	public static void main(String[] args) {
		
		AbstractApplicationContext factory = new ClassPathXmlApplicationContext("spring.xml");
		EmpDaoImpl empDaoImpl=(EmpDaoImpl) factory.getBean("empDaoImpl");
//		empDaoImpl.saveEmp();
		

	}

}
