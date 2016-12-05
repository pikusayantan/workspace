package com.india.sayantan.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.repository.CrudRepository;

import com.india.sayantan.jdbc.model.Addr;
import com.india.sayantan.jdbc.model.Emp;
import com.india.sayantan.jdbc.repo.EmployeeRepo;

@Named
public class EmpDaoImpl {
	
	private static CrudRepository repository;
	
	
	public static void main(String args[]){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("databaseConfig.xml");
        repository = context.getBean(EmployeeRepo.class);
        
		Emp emp=new Emp();
		emp.setAge(10);
		emp.setId(1012);
		emp.setName("sayanta");
		
		Addr addr1=new Addr();
		addr1.setId(emp.getId());
		addr1.setAddress("kolkata");
		Addr addr2=new Addr();
		addr2.setId(emp.getId());
		addr2.setAddress("durga");
		List<Addr> employees=new ArrayList<Addr>();
		employees.add(addr1);
		employees.add(addr2);
		
		
		emp.setEmployees(employees);
		
		System.out.println("calling Constr here");
		Emp re = (Emp)repository.save(emp);
		System.out.println("printing obj "+re.getAge());
	}
	
	/*public void saveEmp(){
		System.out.println("calling here");
		repository = context.getBean(EmployeeRepo.class);
		
		repository.save(emp);
	}*/

}
