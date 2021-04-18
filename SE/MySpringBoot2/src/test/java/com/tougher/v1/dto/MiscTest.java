package com.tougher.v1.dto;

import org.junit.jupiter.api.Test;

import com.tougher.app.v1.model.enums.Gender;

public class MiscTest {
	@Test
	public void gender() {
		Gender.MALE.getDesc();
	}
}
