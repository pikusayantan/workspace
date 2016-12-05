package com.india.sayantan.InjectNamed.Service;

import javax.inject.Inject;
import javax.inject.Named;

import com.india.sayantan.InjectNamed.DaoInterface.Daointerface;
import com.india.sayantan.InjectNamed.DaoInterface.ServiceInterface;

@Named
public class Service implements ServiceInterface{

	@Inject
	Daointerface dao;
	
	public void serviceImpl() {
		dao.print();
		
	}

}
