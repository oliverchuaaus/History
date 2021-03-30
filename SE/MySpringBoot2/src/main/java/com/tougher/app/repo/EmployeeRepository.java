package com.tougher.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tougher.app.model.Employee;
import com.tougher.app.repo.util.EmployeeRepositoryInf;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryInf {

}
