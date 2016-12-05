package com.india.sayantan.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.india.sayantan.util.Area;


public class beanRefInXML {

	public static void main(String[] args) {
		
		ApplicationContext factory = new ClassPathXmlApplicationContext("spring.xml");
		Area area=(Area) factory.getBean("area");
		area.getArea();

	}

}
