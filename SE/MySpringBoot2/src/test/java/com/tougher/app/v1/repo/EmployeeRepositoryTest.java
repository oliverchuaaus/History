package com.tougher.app.v1.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.tougher.app.v1.dto.criteria.EmployeeSearchCriteriaDTO;
import com.tougher.app.v1.model.Address;
import com.tougher.app.v1.model.Employee;
import com.tougher.app.v1.model.PersonalDetail;
import com.tougher.app.v1.model.Phone;
import com.tougher.app.v1.model.ProfessionalDetail;
import com.tougher.app.v1.model.WorkDetail;
import com.tougher.app.v1.model.enums.Gender;

import lombok.extern.log4j.Log4j2;

@Log4j2
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EmployeeRepositoryTest {
	@Autowired
	private EmployeeRepository repo;
	@Autowired
	private OccupationRepository occRepo;
	@Autowired
	private HobbyRepository hobbyRepo;

	@Test
	public void saveMinimal() {
		Employee emp = createMinimal();
		repo.save(emp);
	}

	@Test
	public void saveComplete() {
		Employee emp = createComplete();
		repo.save(emp);
	}

	@Test
	public void saveCompletewithFKs() {
		Employee emp = createCompleteWithFKs();
		repo.save(emp);
	}

	@Test
	public void find() {
		Employee emp = createCompleteWithFKs();
		Employee emp2 = repo.save(emp);
		repo.findById(emp2.getId()).get();
	}

	@Test
	public void findAll() {
		List<Employee> emps = repo.findAll();
		assertEquals(3, emps.size());
	}

	@Test
	public void findByCriteria() {
		EmployeeSearchCriteriaDTO criteria = new EmployeeSearchCriteriaDTO();
		Sort sort = Sort.by(Sort.Order.desc("id"));
		Pageable pageable = PageRequest.of(0, 20, sort);
		Page<Employee> emps = repo.findByCriteria(criteria, pageable);
		assertEquals(3, emps.getContent().size());
		log.info("results: " + emps.getContent());

		pageable = PageRequest.of(0, 2);
		emps = repo.findByCriteria(criteria, pageable);
		List<Employee> list = emps.getContent();
		assertEquals(2, list.size());
		assertEquals(1, list.get(0).getId());
		assertEquals(2, list.get(1).getId());

		assertEquals(0, emps.getNumber());
		assertEquals(2, emps.getTotalElements());
		assertEquals(1, emps.getTotalPages());

		pageable = PageRequest.of(1, 2);
		emps = repo.findByCriteria(criteria, pageable);
		assertEquals(1, emps.getContent().size());
		log.info("results: " + emps.getContent());
	}

	private Employee createMinimal() {
		Employee emp = new Employee();
		emp.setFirstName("Oliver");
		return emp;
	}

	private Employee createComplete() {
		Employee emp = createMinimal();
		emp.setFirstName("Oliver");
		emp.setLastName("Chua");
		emp.setBirthday(LocalDate.of(1977, 6, 20));
		emp.setGender(Gender.MALE);
		return emp;
	}

	private Employee createCompleteWithFKs() {
		Employee emp = createComplete();

		emp.setOccupation(occRepo.getOne(1L));

		emp.getHobbies().add(hobbyRepo.getOne(1L));
		emp.getHobbies().add(hobbyRepo.getOne(2L));

		emp.setPersonalDetail(new PersonalDetail());
		emp.getPersonalDetail().setEmployee(emp);

		emp.setProfessionalDetail(new ProfessionalDetail());
		emp.getProfessionalDetail().setEmployee(emp);

		emp.setWorkDetail(new WorkDetail());

		emp.getAddresses().add(new Address());
		emp.getAddresses().get(0).setEmployee(emp);
		emp.getAddresses().get(0).setPostcode("2131");

		emp.getPhones().add(new Phone());

		return emp;
	}
}
