package com.india.sayantan.main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.india.sayantan.model.Triangle;



public class DrawingApp {

	public static void main(String[] args) {
		
		AbstractApplicationContext factory = new ClassPathXmlApplicationContext("spring.xml");
		factory.registerShutdownHook();
		Triangle triangle=(Triangle) factory.getBean("triangle");
		triangle.draw();
		

	}

}
