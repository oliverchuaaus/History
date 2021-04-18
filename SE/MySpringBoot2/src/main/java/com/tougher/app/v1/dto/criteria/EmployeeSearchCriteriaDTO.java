package com.tougher.app.v1.dto.criteria;

import java.util.List;

import lombok.Data;

@Data
public class EmployeeSearchCriteriaDTO {

	private String name;

	private Long occupationCode;

	private List<Long> hobbyList;

	private String gender;

	private PageableDTO pageable;
}
