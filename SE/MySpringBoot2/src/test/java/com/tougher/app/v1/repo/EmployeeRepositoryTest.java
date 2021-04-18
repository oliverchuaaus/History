package com.tougher.app.v1.repo;

import static com.tougher.app.v1.repo.EmployeeRepository.hasGender;
import static com.tougher.app.v1.repo.EmployeeRepository.hasHobby;
import static com.tougher.app.v1.repo.EmployeeRepository.hasOccupation;
import static com.tougher.app.v1.repo.EmployeeRepository.start;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.tougher.app.v1.dto.criteria.EmployeeSearchCriteriaDTO;
import com.tougher.app.v1.model.Address;
import com.tougher.app.v1.model.Employee;
import com.tougher.app.v1.model.PersonalDetail;
import com.tougher.app.v1.model.Phone;
import com.tougher.app.v1.model.ProfessionalDetail;
import com.tougher.app.v1.model.WorkDetail;
import com.tougher.app.v1.model.enums.Gender;

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
	public void findAllMaleEmployees() {
		List<Employee> emps = repo.findAllMaleEmployees();
		assertEquals(2, emps.size());
	}

	@Test
	public void findByGenders() {
		List<Gender> genders = new ArrayList<Gender>();
		genders.add(Gender.MALE);
		List<Employee> emps = repo.findByGenders(genders);
		assertEquals(2, emps.size());

		genders.add(Gender.FEMALE);
		emps = repo.findByGenders(genders);
		assertEquals(2, emps.size());

		genders.remove(0);
		emps = repo.findByGenders(genders);
		assertEquals(0, emps.size());

		genders.remove(0);
		emps = repo.findByGenders(genders);
		assertEquals(0, emps.size());
	}

	@Test
	public void findByBirthday() {
		LocalDate fromDate = LocalDate.of(1977, 6, 20);
		LocalDate toDate = LocalDate.of(1977, 6, 20);
		List<Employee> emps = repo.findByBirthday(fromDate, toDate);
		assertEquals(2, emps.size());

		fromDate = LocalDate.of(1977, 6, 19);
		toDate = LocalDate.of(1977, 6, 21);
		emps = repo.findByBirthday(fromDate, toDate);
		assertEquals(2, emps.size());

		// Not transitive
		fromDate = LocalDate.of(1977, 6, 21);
		toDate = LocalDate.of(1977, 6, 19);
		emps = repo.findByBirthday(fromDate, toDate);
		assertEquals(0, emps.size());

		fromDate = LocalDate.of(1977, 6, 21);
		toDate = LocalDate.of(1977, 6, 21);
		emps = repo.findByBirthday(fromDate, toDate);
		assertEquals(0, emps.size());
	}

	@Test
	public void findFirstName() {
		String name = "oli";
		List<Employee> emps = repo.findFirstName(name);
		assertEquals(2, emps.size());

		name = "olio";
		emps = repo.findFirstName(name);
		assertEquals(0, emps.size());
	}

	@Test
	public void hasFirstNameMatching() {
		String name = "oli";
		List<Employee> emps = repo.findAll(EmployeeRepository.hasFirstNameMatching(name));
		assertEquals(2, emps.size());

		name = "olio";
		emps = repo.findAll(EmployeeRepository.hasFirstNameMatching(name));
		assertEquals(0, emps.size());
	}

	@Test
	public void hasLastNameMatching() {
		String name = "HU";
		List<Employee> emps = repo.findAll(EmployeeRepository.hasLastNameMatching(name));
		assertEquals(2, emps.size());

		name = "olio";
		emps = repo.findAll(EmployeeRepository.hasLastNameMatching(name));
		assertEquals(0, emps.size());
	}

	@Test
	public void specHasGenders() {
		List<Gender> genders;
		List<Employee> emps;

		genders = new ArrayList<Gender>();
		genders.add(Gender.MALE);
		emps = repo.findAll(EmployeeRepository.hasGenders(genders));
		assertEquals(2, emps.size());

		genders.add(Gender.FEMALE);
		emps = repo.findAll(EmployeeRepository.hasGenders(genders));
		assertEquals(2, emps.size());

		genders.remove(0);
		emps = repo.findAll(EmployeeRepository.hasGenders(genders));
		assertEquals(0, emps.size());

		genders.remove(0);
		emps = repo.findAll(EmployeeRepository.hasGenders(genders));
		assertEquals(0, emps.size());
	}

	@Test
	public void specHasAll() {
		Specification<Employee> spec;
		List<Employee> emps;

		spec = start();
		emps = repo.findAll(spec);
		assertEquals(3, emps.size());
	}

	@Test
	public void specHasGender() {
		Specification<Employee> spec;
		List<Employee> emps;

		spec = hasGender(Gender.MALE);
		emps = repo.findAll(spec);
		assertEquals(2, emps.size());

		spec = hasGender(Gender.FEMALE);
		emps = repo.findAll(spec);
		assertEquals(0, emps.size());
		spec = hasGender(null);
		emps = repo.findAll(spec);
		assertEquals(0, emps.size());
	}

	@Test
	public void specHasOccupation() {
		Specification<Employee> spec;
		List<Employee> emps;

		spec = hasOccupation(1L);
		emps = repo.findAll(spec);
		assertEquals(1, emps.size());

		spec = hasOccupation(2L);
		emps = repo.findAll(spec);
		assertEquals(0, emps.size());

		spec = hasOccupation(null);
		emps = repo.findAll(spec);
		assertEquals(0, emps.size());
	}

	@Test
	public void specHasHobby() {
		Specification<Employee> spec;
		List<Employee> emps;
		List<Long> hobbyList = new ArrayList<>();
		hobbyList.add(1L);

		spec = hasHobby(hobbyList);
		emps = repo.findAll(spec);
		assertEquals(1, emps.size());

		hobbyList.clear();
		spec = hasHobby(hobbyList);
		emps = repo.findAll(spec);
		assertEquals(0, emps.size());

//		spec = hasHobby(null);
//		emps = repo.findAll(spec);
//		assertEquals(0, emps.size());
	}

	@Test
	public void findByCriteria() {
		// Check at least one
		EmployeeSearchCriteriaDTO criteria;
		Page<Employee> result;
		PageRequest pageable = PageRequest.of(0, 20);

		criteria = new EmployeeSearchCriteriaDTO();
		result = repo.findByCriteria(criteria, pageable);
		assertEquals(3, result.getNumberOfElements());
		
		criteria = new EmployeeSearchCriteriaDTO();
		criteria.setName("oli");
		result = repo.findByCriteria(criteria, pageable);
		assertEquals(2, result.getNumberOfElements());
		
		criteria = new EmployeeSearchCriteriaDTO();
		criteria.setName("hu");
		result = repo.findByCriteria(criteria, pageable);
		assertEquals(2, result.getNumberOfElements());
		
		criteria = new EmployeeSearchCriteriaDTO();
		criteria.setGender(Gender.MALE.name());
		result = repo.findByCriteria(criteria, pageable);
		assertEquals(2, result.getNumberOfElements());
		
		criteria = new EmployeeSearchCriteriaDTO();
		criteria.setOccupationCode(1L);
		result = repo.findByCriteria(criteria, pageable);
		assertEquals(1, result.getNumberOfElements());

		criteria = new EmployeeSearchCriteriaDTO();
		criteria.setHobbyList(Arrays.asList(1L));
		result = repo.findByCriteria(criteria, pageable);
		assertEquals(1, result.getNumberOfElements());
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
