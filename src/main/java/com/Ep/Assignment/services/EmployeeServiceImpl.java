package com.Ep.Assignment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ep.Assignment.dao.EmployeeRepository;
import com.Ep.Assignment.entities.Employee;
import com.Ep.Assignment.exceptions.EmployeeNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	// Create employee
	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	// Get all employees
	@Override
	public List<Employee> getAllEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}

	// find employees by ID and return Optional
	@Override
	public Optional<Employee> getEmployeeById(int empID) {
		return employeeRepository.findById(empID);
	}

	// find employees by lastName and throw exception if not present
	@Override
	public List<Employee> getEmployeesByLastName(String lastName) {
		Optional<List<Employee>> employees = employeeRepository.findByLastName(lastName);
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException(lastName);
		}
		return employees.get();
	}

	// update an employee
	@Override
	public Employee updateEmployee(int empID, Employee newEmployeeData) {
		Optional<Employee> oldEmployee = employeeRepository.findById(empID);
		if (oldEmployee.isPresent()) {
			Employee employee = oldEmployee.get();
			employee.setFirstName(newEmployeeData.getFirstName());
			employee.setLastName(newEmployeeData.getLastName());
			employee.setEmail(newEmployeeData.getEmail());
			return employeeRepository.save(employee);
		} else {
			throw new EmployeeNotFoundException(empID);
		}
	}

	// delete an employee by id
	@Override
	public void deleteEmployee(int empID) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(empID);
		if (optionalEmployee.isPresent()) {
			employeeRepository.deleteById(empID);
		} else {
			throw new EmployeeNotFoundException(empID);
		}
	}
}
