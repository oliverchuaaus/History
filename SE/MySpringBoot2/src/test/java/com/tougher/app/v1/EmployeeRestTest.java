package com.tougher.app.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import com.tougher.app.v1.dto.AddressDTO;
import com.tougher.app.v1.dto.EmployeeDTO;
import com.tougher.app.v1.dto.PersonalDetailDTO;
import com.tougher.app.v1.dto.ProfessionalDetailDTO;
import com.tougher.app.v1.dto.WorkDetailDTO;
import com.tougher.app.v1.dto.criteria.EmployeeSearchCriteriaDTO;
import com.tougher.app.v1.dto.criteria.EmployeeSearchResultDTO;
import com.tougher.app.v1.dto.criteria.PageableDTO;
import com.tougher.app.v1.dto.criteria.PageableDTO.SortDTO;
import com.tougher.app.v1.dto.criteria.PageableDTO.SortDTO.NullHandlingDTO;
import com.tougher.app.v1.dto.ref.HobbyDTO;
import com.tougher.app.v1.dto.ref.OccupationDTO;

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
		Assertions.assertThat(emp2).usingRecursiveComparison().ignoringFieldsMatchingRegexes("id", "addresses.id",
				"personalDetail.id", "professionalDetail.id", "workDetail.id", "occupation.occupation", "hobbies.hobby")
				.isEqualTo(emp);
	}

	@Test
	public void saveCompleteWithFKsBadValidation() throws JsonMappingException, JsonProcessingException {
		EmployeeDTO emp;
		ResponseEntity<String> response;
		ObjectMapper objectMapper;
		Map<String, String> error;
		
		emp = createCompleteWithFKs();
		emp.setFirstName(null);
		emp.getAddresses().get(0).setPostcode(null);
		response = rest.postForEntity(getURL(), emp, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		objectMapper = new ObjectMapper();
		error = objectMapper.readValue(response.getBody(),
				new TypeReference<Map<String, String>>() {
				});
		assertEquals("must not be null", error.get("firstName"));
		assertEquals("must not be null", error.get("addresses[0].postcode"));
		
		emp = createCompleteWithFKs();
		emp.getAddresses().get(0).setSuburb("1234567890123456789012345678901");
		response = rest.postForEntity(getURL(), emp, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		objectMapper = new ObjectMapper();
		error = objectMapper.readValue(response.getBody(),
				new TypeReference<Map<String, String>>() {
				});
		assertEquals("size must be between 0 and 30", error.get("suburb"));
	}

	@Test
	public void findById() {
		EmployeeDTO emp = createCompleteWithFKs();
		EmployeeDTO emp2 = rest.postForEntity(getURL(), emp, EmployeeDTO.class).getBody();
		EmployeeDTO emp3 = rest.getForEntity(getURL() + emp2.getId(), EmployeeDTO.class).getBody();
		Assertions.assertThat(emp3).usingRecursiveComparison()
				.ignoringFieldsMatchingRegexes("id", "addresses.id", "personalDetail.id", "personalDetail.employee",
						"professionalDetail.id", "professionalDetail.employee", "workDetail.id",
						"occupation.occupation", "hobbies.hobby", "addresses.employee")
				.isEqualTo(emp);
	}

	@Test
	public void findByCriteria() {
		// Check at least one
		EmployeeSearchCriteriaDTO criteria;
		EmployeeSearchResultDTO result;

		criteria = new EmployeeSearchCriteriaDTO();
		result = rest.postForEntity(getURL() + "search", criteria, EmployeeSearchResultDTO.class).getBody();
		assertEquals(3, result.getEmployeeList().size());
		
		criteria = new EmployeeSearchCriteriaDTO();
		criteria.setPageable(new PageableDTO());
		criteria.getPageable().setPageSize(1);
		criteria.getPageable().setOffset(0);
		criteria.getPageable().setSort(new SortDTO());
		List<PageableDTO.SortDTO.OrderDTO> list= new ArrayList<PageableDTO.SortDTO.OrderDTO>();
		PageableDTO.SortDTO.OrderDTO order = new PageableDTO.SortDTO.OrderDTO();
		order.setDirection(PageableDTO.SortDTO.DirectionDTO.ASC);
		order.setProperty("lastName");
		order.setNullHandling(NullHandlingDTO.NULLS_FIRST);
		list.add(order);
		
		criteria.getPageable().getSort().setOrderList(list);
		result = rest.postForEntity(getURL() + "search", criteria, EmployeeSearchResultDTO.class).getBody();
		assertEquals(1, result.getEmployeeList().size());

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
		emp.setGender("MALE");
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
