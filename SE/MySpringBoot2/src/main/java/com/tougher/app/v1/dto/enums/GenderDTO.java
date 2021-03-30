package com.tougher.app.v1.dto.enums;

import lombok.Getter;

@Getter
public enum GenderDTO {
	MALE("M", "Male"), FEMALE("F", "Female"), INDETERMINATE("X", "Indeterminate");

	String code;
	String desc;

	GenderDTO(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

}
