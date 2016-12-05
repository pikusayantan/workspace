package com.india.sayantan.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.india.sayantan.InjectNamed.Service.Service;

public class InjectionMain {

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("spring.xml");
		Service service=(Service) factory.getBean("service");
		service.serviceImpl();
		

	}

}
