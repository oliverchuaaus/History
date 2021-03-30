package com.tougher.app.dto.criteria;

import java.util.List;

import com.tougher.app.dto.EmployeeDTO;

import lombok.Data;

public @Data class EmployeeSearchResultDTO {
	private List<EmployeeDTO> employeeList;
	private PageResultMetadataDTO metadata;
}
