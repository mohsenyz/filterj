package org.filterj.example.dao.dao;

import org.filterj.example.model.Employee;

import java.util.List;

public interface EmployeeDao {

	void saveEmployee(Employee employee);
	
	List<Employee> findAllEmployees();
	
	void deleteEmployeeBySsn(String ssn);
	
	Employee findBySsn(String ssn);

	List<Employee> findEmployees();
	
	void updateEmployee(Employee employee);
}
