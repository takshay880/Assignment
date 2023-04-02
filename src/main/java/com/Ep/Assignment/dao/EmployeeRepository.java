package com.Ep.Assignment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Ep.Assignment.entities.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	Optional<List<Employee>> findByLastName(String lastName);
}
