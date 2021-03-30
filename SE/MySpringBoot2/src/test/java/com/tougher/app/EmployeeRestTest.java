package com.tougher.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tougher.app.dto.AddressDTO;
import com.tougher.app.dto.EmployeeDTO;
import com.tougher.app.dto.PersonalDetailDTO;
import com.tougher.app.dto.ProfessionalDetailDTO;
import com.tougher.app.dto.WorkDetailDTO;
import com.tougher.app.dto.criteria.EmployeeSearchCriteriaDTO;
import com.tougher.app.dto.criteria.EmployeeSearchResultDTO;
import com.tougher.app.dto.enums.GenderDTO;
import com.tougher.app.dto.ref.HobbyDTO;
import com.tougher.app.dto.ref.OccupationDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeRestTest {
	@Autowired
	private TestRestTemplate rest;
	@LocalServerPort
	int port;

	private String getURL() {
		return "http://localhost:" + port + "/employees/";
	}

	@Test
	public void saveCompleteWithFKs() {
		EmployeeDTO emp = createCompleteWithFKs();
		EmployeeDTO emp2 = rest.postForEntity(getURL(), emp, EmployeeDTO.class).getBody();
		Assertions
				.assertThat(emp2).usingRecursiveComparison().ignoringFieldsMatchingRegexes("id", "addresses.id",
						"personalDetail.id", "professionalDetail.id", "workDetail.id","occupation.occupation", "hobbies.hobby")
				.isEqualTo(emp);
	}
	
	@Test
	public void saveCompleteWithFKsBadValidation() throws JsonMappingException, JsonProcessingException {
		EmployeeDTO emp = createCompleteWithFKs();
		emp.setFirstName(null);
		emp.getAddresses().get(0).setPostcode(null);
		ResponseEntity<String> response = rest.postForEntity(getURL(), emp, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> error= objectMapper.readValue(response.getBody(), new TypeReference<Map<String,String>>(){});
		assertEquals("must not be null", error.get("firstName"));
		assertEquals("must not be null", error.get("addresses[0].postcode"));
	}
	
	@Test
	public void findById() {
		EmployeeDTO emp = createCompleteWithFKs();
		EmployeeDTO emp2 = rest.postForEntity(getURL(), emp, EmployeeDTO.class).getBody();
		EmployeeDTO emp3 = rest.getForEntity(getURL() + emp2.getId(), EmployeeDTO.class).getBody();
		Assertions.assertThat(emp3).usingRecursiveComparison()
				.ignoringFieldsMatchingRegexes("id", "addresses.id", "personalDetail.id", "personalDetail.employee",
						"professionalDetail.id", "professionalDetail.employee", "occupation.occupation",
						"hobbies.hobby", "addresses.employee")
				.isEqualTo(emp2);
	}

	@Test
	public void findByCriteria() {
		// Create one
		EmployeeDTO emp = createCompleteWithFKs();
		rest.postForEntity(getURL(), emp, EmployeeDTO.class).getBody();

		// Check at least one
		EmployeeSearchCriteriaDTO criteria = new EmployeeSearchCriteriaDTO();
		EmployeeSearchResultDTO result = rest.postForEntity(getURL() + "search", criteria, EmployeeSearchResultDTO.class)
				.getBody();
		assertTrue(result.getEmployeeList().size() > 1);
	}

	private EmployeeDTO createMinimal() {
		EmployeeDTO emp = new EmployeeDTO();
		emp.setFirstName("Oliver");
		return emp;
	}

	private EmployeeDTO createComplete() {
		EmployeeDTO emp = createMinimal();
		emp.setLastName("Chua");
		emp.setBirthday(LocalDate.of(1977, 6, 20));
		emp.setGender(GenderDTO.MALE);
		return emp;
	}

	private EmployeeDTO createCompleteWithFKs() {
		EmployeeDTO emp = createComplete();

		emp.setOccupation(new OccupationDTO());
		emp.getOccupation().setId(1L);

		emp.getHobbies().add(new HobbyDTO());
		emp.getHobbies().add(new HobbyDTO());
		emp.getHobbies().get(0).setId(1L);
		emp.getHobbies().get(1).setId(2L);

		emp.setPersonalDetail(new PersonalDetailDTO());
		emp.getPersonalDetail().setRequiredDetail("detail");

		emp.setProfessionalDetail(new ProfessionalDetailDTO());
		emp.getProfessionalDetail().setDetail("detail");
		
		emp.setWorkDetail(new WorkDetailDTO());
		emp.getWorkDetail().setDetail("detail");

		emp.getAddresses().add(new AddressDTO());
		emp.getAddresses().get(0).setPostcode("2131");

		return emp;
	}

}
