package com.tougher.app.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

public @Data class AddressDTO {
	private Long id;
	private String unitNumber;
	private String street;
	private String suburb;
	@NotNull
	private String postcode;
}
