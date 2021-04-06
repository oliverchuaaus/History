package com.tougher.app.v1.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tougher.app.v1.model.Employee;
import com.tougher.app.v1.model.enums.Gender;
import com.tougher.app.v1.repo.impl.EmployeeRepositoryInf;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryInf {

	@Query("SELECT e FROM Employee e WHERE e.gender = com.tougher.app.v1.model.enums.Gender.MALE")
	List<Employee> findAllMaleEmployees();

	@Query("SELECT e FROM Employee e WHERE e.gender IN :genders")
	List<Employee> findByGenders(List<Gender> genders);

	@Query("SELECT e FROM Employee e WHERE e.birthday BETWEEN :fromDate AND :toDate")
	List<Employee> findByBirthday(LocalDate fromDate, LocalDate toDate);

	//Using NamedQuery. NamedQuery should be named Entity.methodName. 
	List<Employee> findFirstName(@Param("name") String firstName);

}
