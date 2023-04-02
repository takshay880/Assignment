package com.Ep.Assignment.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(Integer empID) {
		super("Employee not found with empID - " + empID);
	}

	public EmployeeNotFoundException(String lastName) {
		super("Employees not found with lastName - " + lastName);
	}
}