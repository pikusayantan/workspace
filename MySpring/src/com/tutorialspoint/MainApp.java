package com.tutorialspoint;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	private static AbstractApplicationContext context;
	private static AbstractApplicationContext context2;

	public static void main(String[] args) {
		System.out.println("in main");
		context = new ClassPathXmlApplicationContext("Beans.xml");
		System.out.println("after application contxt");
		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		System.out.println("after HelloWorld obj");
		obj.setMessage("hello changed");
		obj.getMessages();
		obj.getMessages();
		context2 = new ClassPathXmlApplicationContext("Beans.xml");
		context.close();
		System.out.println("duplicate obj starting here");
		HelloWorld obj2 = (HelloWorld) context2.getBean("helloWorld");
		obj2.getMessages();
		HelloWorld obj3 = (HelloWorld) context2.getBean("helloWorld");
		obj3.setMessage("hello changed duplicate");
		

	}
}