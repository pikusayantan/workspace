package com.india.sayantan.jdbc.repo;

import org.springframework.data.repository.CrudRepository;

import com.india.sayantan.jdbc.model.Emp;

public interface EmployeeRepo extends CrudRepository<Emp, Integer>{

}
