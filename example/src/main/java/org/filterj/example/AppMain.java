package org.filterj.example;

import org.filterj.api.FilterAPI;
import org.filterj.example.configuration.AppConfig;
import org.filterj.example.model.Employee;
import org.filterj.example.service.EmployeeService;
import org.joda.time.LocalDate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class AppMain {

	public static void main(String args[]) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		EmployeeService service = (EmployeeService) context.getBean("employeeService");

		/*
		 * Create Employee1
		 */
		Employee employee1 = new Employee();
		employee1.setName("Mehdi Afsari");
		employee1.setJoiningDate(new LocalDate(2010, 10, 10));
		employee1.setSalary(new BigDecimal(10000));
		employee1.setSsn("ssn00000001");

		/*
		 * Create Employee2
		 */
		Employee employee2 = new Employee();
		employee2.setName("Ali Alizadeh");
		employee2.setJoiningDate(new LocalDate(2012, 11, 11));
		employee2.setSalary(new BigDecimal(20000));
		employee2.setSsn("ssn00000002");

		/*
		 * Persist both Employees
		 */
		service.saveEmployee(employee1);
		service.saveEmployee(employee2);

		/*
		 * Get all employees list from database
		 */
		List<Employee> employees = service.findAllEmployees();
		for (Employee emp : employees) {
			System.out.println(emp);
		}

		/*
		 * delete an employee
		 */
		service.deleteEmployeeBySsn("ssn00000002");

		/*
		 * update an employee
		 */

		Employee employee = service.findBySsn("ssn00000001");
		employee.setSalary(new BigDecimal(50000));
		service.updateEmployee(employee);

		/*
		 * Get all employees list from database
		 */
		List<Employee> employeeList = service.findAllEmployees();
		for (Employee emp : employeeList) {
			System.out.println(emp);
		}

		FilterAPI.print();


		context.close();
	}
}
