package org.filterj.example.service;

import org.filterj.example.dao.dao.EmployeeDao;
import org.filterj.example.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao dao;
	
	public void saveEmployee(Employee employee) {
		dao.saveEmployee(employee);
	}

	public List<Employee> findAllEmployees() {
		return dao.findAllEmployees();
	}

	public void deleteEmployeeBySsn(String ssn) {
		dao.deleteEmployeeBySsn(ssn);
	}

	public Employee findBySsn(String ssn) {
		return dao.findBySsn(ssn);
	}

	public void updateEmployee(Employee employee){
		dao.updateEmployee(employee);
	}
}
