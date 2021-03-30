package com.tougher.app.dto.criteria;

import java.util.List;

import com.tougher.app.dto.enums.GenderDTO;

import lombok.Data;

public @Data class EmployeeSearchCriteriaDTO {
	
	private String occupationCode;
	
	private List<String> hobbyList;
	
	private GenderDTO gender;
	
	private PageableDTO pageable; 
}
