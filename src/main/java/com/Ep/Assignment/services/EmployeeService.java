package com.Ep.Assignment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Ep.Assignment.entities.Employee;

@Service
public interface EmployeeService {

	public Employee createEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public Optional<Employee> getEmployeeById(int empID);

	public List<Employee> getEmployeesByLastName(String lastName);

	public Employee updateEmployee(int empID, Employee employeeDetails);

	public void deleteEmployee(int empId);

}
