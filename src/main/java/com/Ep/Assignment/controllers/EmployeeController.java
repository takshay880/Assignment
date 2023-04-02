package com.Ep.Assignment.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Ep.Assignment.entities.Employee;
import com.Ep.Assignment.exceptions.EmployeeNotFoundException;
import com.Ep.Assignment.services.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// get all employees
	@GetMapping("")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	// get employee by ID
	@GetMapping("/{empID}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int empID) {
		Optional<Employee> employee = employeeService.getEmployeeById(empID);
		if (employee.isPresent()) {
			return new ResponseEntity<>(employee.get(), HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// find list of employees by lastName
	@GetMapping("/bylastname/{lastName}")
	public ResponseEntity<List<Employee>> getEmployeesByLastName(@PathVariable String lastName) {
		List<Employee> employees = employeeService.getEmployeesByLastName(lastName);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	// handle EmployeeNotFoundException
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);

	}

	// create a new employee
	@PostMapping("")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		if (employee == null) {
			return ResponseEntity.badRequest().build();
		}
		Employee savedEmployee = employeeService.createEmployee(employee);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}

	// update an existing employee
	@PutMapping("/{empID}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int empID, @RequestBody Employee employeeDetails) {
		if (employeeDetails == null) {
			return ResponseEntity.badRequest().build();
		}
		Employee updatedEmployee = employeeService.updateEmployee(empID, employeeDetails);
		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	}

	// delete an existing employee
	@DeleteMapping("/{empID}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int empID) {
		if (employeeService.getEmployeeById(empID).isPresent()) {
			employeeService.deleteEmployee(empID);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
