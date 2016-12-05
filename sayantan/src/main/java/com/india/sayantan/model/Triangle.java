package com.india.sayantan.model;

import javax.inject.Inject;
import javax.inject.Qualifier;
import javax.persistence.Id;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Triangle implements InitializingBean,DisposableBean{
	/*@Inject
	Points length2;*/
	
	private String type;
	private int height;

	public Triangle(String type) {
		this.type = type;

	}

	public Triangle(int height) {
		this.height = height;

	}
	
	public Triangle( String type, int height) {
		this.height = height;
		this.type = type;

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void draw() {
		System.out.println("Triangle drawn : " + type+" height : "+height);
//		System.out.println("From points "+length2.getPointA());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Triangle Bean instialization InitializingBean called");
		
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("Traingle Bean destroy DisposableBean called");
		
	}

}
