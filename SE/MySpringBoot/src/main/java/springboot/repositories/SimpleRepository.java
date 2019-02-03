package springboot.repositories;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springboot.entities.NamesOnly;
import springboot.entities.NamesOnlyDTO;
import springboot.entities.NamesOnlyDefaultMethod;
import springboot.entities.NamesOnlyValueAnnotation;
import springboot.entities.Simple;

@Repository
public interface SimpleRepository extends CrudRepository<Simple, Long>{
	
	// String - no param
	public List<Simple> findByFirstNameIsNull();

	public List<Simple> findByFirstNameIsNotNull();

	// String - single exact
	public List<Simple> findByFirstName(String name);

	public List<Simple> findByFirstNameIs(String name);

	public List<Simple> findByFirstNameEquals(String name);

	public List<Simple> findByFirstNameIgnoreCase(String name);

	public List<Simple> findByFirstNameNot(String name);

	// String - single fuzzy
	public List<Simple> findByFirstNameLike(String name);

	public List<Simple> findByFirstNameNotLike(String name);

	public List<Simple> findByFirstNameStartingWith(String name);

	public List<Simple> findByFirstNameEndingWith(String name);

	public List<Simple> findByFirstNameContaining(String name);

	// String - multiple
	public List<Simple> findByFirstNameAndLastName(String name1, String name2);

	public List<Simple> findByFirstNameOrLastName(String name1, String name2);

	public List<Simple> findByFirstNameAndLastNameAllIgnoreCase(String name1, String name2);

	public List<Simple> findByFirstNameIn(Collection<String> names);

	public List<Simple> findByFirstNameNotIn(Collection<String> names);

	// Dates
	public List<Simple> findByBirthDateBetween(LocalDate from, LocalDate to);

	public List<Simple> findByBirthDateBefore(LocalDate date);

	public List<Simple> findByBirthDateAfter(LocalDate date);

	// Number
	public List<Simple> findByAgeLessThan(int age);

	public List<Simple> findByAgeLessThanEqual(int age);

	public List<Simple> findByAgeGreaterThan(int age);

	public List<Simple> findByAgeGreaterThanEqual(int age);

	// Boolean
	public List<Simple> findByRegisteredTrue();

	public List<Simple> findByRegisteredFalse();

	// Named Query
	public List<Simple> findByLastName(String name);

	// Named Native Query
	public List<Simple> findByLastNameNamedNative(String name);

	// Query
	@Query("select u from Simple u where u.birthDate = ?1")
	public List<Simple> findByBirthDate(LocalDate date);

	// Query Like
	@Query("select u from Simple u where u.lastName like %?1")
	public List<Simple> findByLastNameEndsWith(String lastName);

	// Query Native
	@Query(nativeQuery = true, value = "select * from Simple where last_name = ?1")
	public List<Simple> findByLastNameNative(String name);

	// Sort
	public List<Simple> findAllByOrderByFirstName();

	//Note: workaround is to add a query for this to not throw exception for JpaSort.unsafe call
	@Query("select u from Simple u")
	public List<Simple> findAll(Sort sort);

	@Query("select length(firstName) as fnLength, firstName from Simple u")
	public List<Object> findAllAliasedFunction(Sort sort);

	// Pagination
	public List<Simple> findAll(Pageable pageable);

	// Modifying
	@Modifying()
	@Transactional
	@Query("update Simple u set u.firstName = ?1 where u.lastName = ?2")
	public int updateFirstNameForLastName(String firstname, String lastname);

	@Transactional
	public int deleteByLastName(String lastname);

	@Modifying()
	@Transactional
	@Query("delete from Simple u where u.lastName = ?1")
	public int deleteInBulkByLastName(String lastname);

	// Projection
	public List<NamesOnly> findByAge(int age);

	public List<NamesOnlyDTO> findByAgeOrderByFirstName(int age);

	public List<NamesOnlyValueAnnotation> findByAgeOrderByAge(int age);

	public List<NamesOnlyDefaultMethod> findByAgeOrderByLastName(int age);

}
