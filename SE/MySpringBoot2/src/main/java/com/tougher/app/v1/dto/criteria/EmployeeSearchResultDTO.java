package com.tougher.app.v1.dto.criteria;

import java.util.List;

import com.tougher.app.v1.dto.EmployeeDTO;

import lombok.Data;

@Data
public class EmployeeSearchResultDTO {
	private List<EmployeeDTO> employeeList;
	private PageResultMetadataDTO metadata;
}
