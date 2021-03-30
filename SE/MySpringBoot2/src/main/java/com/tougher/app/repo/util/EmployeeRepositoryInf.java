package com.tougher.app.repo.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tougher.app.dto.criteria.EmployeeSearchCriteriaDTO;
import com.tougher.app.model.Employee;

public interface EmployeeRepositoryInf {
	public Page<Employee> findByCriteria(EmployeeSearchCriteriaDTO dto, Pageable pageable);
}
