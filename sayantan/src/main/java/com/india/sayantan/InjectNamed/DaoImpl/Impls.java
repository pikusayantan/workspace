package com.india.sayantan.InjectNamed.DaoImpl;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.india.sayantan.InjectNamed.DaoInterface.Daointerface;
import com.india.sayantan.InjectNamed.DaoInterface.ServiceInterface;

@Primary
@Component
public class Impls implements Daointerface{
	
	@Inject
	ServiceInterface service;

	int count=0;
	@Override
	public void print() {
		count++;
		System.out.println("Inside dao impl class: "+count);
		
	}

}
