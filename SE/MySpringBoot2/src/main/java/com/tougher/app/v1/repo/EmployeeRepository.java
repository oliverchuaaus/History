package com.tougher.app.v1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tougher.app.v1.model.Employee;
import com.tougher.app.v1.repo.util.EmployeeRepositoryInf;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryInf {

}
