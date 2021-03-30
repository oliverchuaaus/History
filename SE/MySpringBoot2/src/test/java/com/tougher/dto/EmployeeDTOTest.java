package com.tougher.dto;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.tougher.app.dto.EmployeeDTO;
import com.tougher.app.model.Employee;

public class EmployeeDTOTest {
	@Test
	public void employee() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.createTypeMap(Employee.class, EmployeeDTO.class);
		modelMapper.validate();
	}
}
