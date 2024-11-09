package com.javainuse.service;

import java.util.List;

import com.javainuse.dao.EmployeeDAO;
import com.javainuse.domain.Employee;

public class EmployeeService {
	EmployeeDAO empDAO = new EmployeeDAO();

	public void addNewEmployee(Employee emp) {
		empDAO.addNewEmployee(emp);
	}

	public List<Employee> getEmployees() {
		return empDAO.getAllEmployees();
	}

}
