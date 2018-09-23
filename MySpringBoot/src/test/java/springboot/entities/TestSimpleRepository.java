package springboot.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSimpleRepository {

	@Autowired
	private SimpleRepository simpleRepository;

	private Simple createSimple() {
		Simple simple = new Simple();
		simple.setFirstName("firstName");
		simple.setLastName("lastName");
		simple.setBirthDate(LocalDate.of(1977, 6, 20));
		simple.setBirthTime(LocalTime.of(18, 20, 30));
		simple.setAge(25);
		simple.setRegistered(true);
		return simple;
	}

	@Before
	public void init() {
		simpleRepository.save(createSimple());
	}

	@After
	public void tearDown() {
		simpleRepository.deleteAll();
	}

	@Test
	public void testCrud() {
		Simple simple = createSimple();
		simple.setFirstName("firstName");
		simpleRepository.save(simple);
		assertTrue(simpleRepository.findById(simple.getId()).isPresent());
		assertEquals("firstName", simpleRepository.findById(simple.getId()).get().getFirstName());

		simple.setFirstName("firstName2");
		simpleRepository.save(simple);
		assertEquals("firstName2", simpleRepository.findById(simple.getId()).get().getFirstName());

		simpleRepository.delete(simple);
		assertFalse(simpleRepository.findById(simple.getId()).isPresent());
	}

	@Test
	public void testDefaultSave() {
		Simple simple1 = createSimple();
		Simple simple2 = createSimple();
		simpleRepository.saveAll(Arrays.asList(simple1, simple2));
		assertEquals(3, simpleRepository.findAllByOrderByFirstName().size());
	}

	@Test
	public void testDefaultDelete() {
		Simple simple1 = createSimple();
		Simple simple2 = createSimple();
		simpleRepository.saveAll(Arrays.asList(simple1, simple2));
		assertEquals(3, simpleRepository.findAllByOrderByFirstName().size());

		simpleRepository.deleteAll(Arrays.asList(simple1, simple2));
		assertEquals(1, simpleRepository.findAllByOrderByFirstName().size());

		simple1 = createSimple();
		simpleRepository.save(simple1);
		assertEquals(2, simpleRepository.findAllByOrderByFirstName().size());
		simpleRepository.deleteById(simple1.getId());
		assertEquals(1, simpleRepository.findAllByOrderByFirstName().size());

		simple1 = createSimple();
		simpleRepository.save(simple1);
		assertEquals(2, simpleRepository.findAllByOrderByFirstName().size());
		simpleRepository.delete(simple1);
		assertEquals(1, simpleRepository.findAllByOrderByFirstName().size());

		simpleRepository.deleteAll();
		assertEquals(0, simpleRepository.findAllByOrderByFirstName().size());
	}

	@Test
	public void testDefaultFind() {
		Simple simple1 = createSimple();
		Simple simple2 = createSimple();
		simpleRepository.saveAll(Arrays.asList(simple1, simple2));
		assertEquals(3, simpleRepository.findAllByOrderByFirstName().size());

		assertTrue(simpleRepository.existsById(simple1.getId()));
		assertTrue(simpleRepository.findById(simple1.getId()).isPresent());

		Iterable<Simple> iterator;
		iterator = simpleRepository.findAll();
		assertEquals(3, iterator.spliterator().getExactSizeIfKnown());

		iterator = simpleRepository.findAllById(Arrays.asList(simple1.getId(), simple2.getId()));
		assertEquals(2, iterator.spliterator().getExactSizeIfKnown());

		assertEquals(3, simpleRepository.count());
	}

	@Test
	public void testFindString() {
		// String - no param
		assertFalse(simpleRepository.findByFirstNameIsNotNull().isEmpty());
		assertTrue(simpleRepository.findByFirstNameIsNull().isEmpty());

		// String - single exact
		assertFalse(simpleRepository.findByFirstName("firstName").isEmpty());
		assertTrue(simpleRepository.findByFirstName("firstName1").isEmpty());
		assertFalse(simpleRepository.findByFirstNameIs("firstName").isEmpty());
		assertTrue(simpleRepository.findByFirstNameIs("firstName1").isEmpty());
		assertFalse(simpleRepository.findByFirstNameEquals("firstName").isEmpty());
		assertTrue(simpleRepository.findByFirstNameEquals("firstName1").isEmpty());
		assertFalse(simpleRepository.findByFirstNameIgnoreCase("firstname").isEmpty());
		assertTrue(simpleRepository.findByFirstNameIgnoreCase("firstname1").isEmpty());
		assertFalse(simpleRepository.findByFirstNameNot("firstName1").isEmpty());
		assertTrue(simpleRepository.findByFirstNameNot("firstName").isEmpty());

		// String - single fuzzy
		assertFalse(simpleRepository.findByFirstNameLike("first%ame").isEmpty());
		assertTrue(simpleRepository.findByFirstNameLike("first1%ame").isEmpty());
		assertFalse(simpleRepository.findByFirstNameNotLike("first1%ame").isEmpty());
		assertTrue(simpleRepository.findByFirstNameNotLike("first%ame").isEmpty());
		assertFalse(simpleRepository.findByFirstNameStartingWith("first").isEmpty());
		assertTrue(simpleRepository.findByFirstNameStartingWith("first1").isEmpty());
		assertFalse(simpleRepository.findByFirstNameEndingWith("Name").isEmpty());
		assertTrue(simpleRepository.findByFirstNameEndingWith("Name1").isEmpty());
		assertFalse(simpleRepository.findByFirstNameContaining("stNam").isEmpty());
		assertTrue(simpleRepository.findByFirstNameContaining("stNam1").isEmpty());

		// String - multiple
		assertFalse(simpleRepository.findByFirstNameAndLastName("firstName", "lastName").isEmpty());
		assertTrue(simpleRepository.findByFirstNameAndLastName("firstName1", "lastName").isEmpty());
		assertFalse(simpleRepository.findByFirstNameOrLastName("firstName1", "lastName").isEmpty());
		assertTrue(simpleRepository.findByFirstNameOrLastName("firstName1", "lastName1").isEmpty());
		assertFalse(simpleRepository.findByFirstNameAndLastNameAllIgnoreCase("firstname", "lastname").isEmpty());
		assertTrue(simpleRepository.findByFirstNameAndLastNameAllIgnoreCase("firstname1", "lastname").isEmpty());
		assertFalse(simpleRepository.findByFirstNameIn(Arrays.asList("firstName", "lastName")).isEmpty());
		assertTrue(simpleRepository.findByFirstNameIn(Arrays.asList("firstName1", "lastName")).isEmpty());
		assertFalse(simpleRepository.findByFirstNameNotIn(Arrays.asList("firstName1", "lastName")).isEmpty());
		assertTrue(simpleRepository.findByFirstNameNotIn(Arrays.asList("firstName", "lastName")).isEmpty());
	}

	@Test
	public void testFindDate() {
		assertFalse(simpleRepository.findByBirthDateBetween(LocalDate.of(1977, 6, 19), LocalDate.of(1977, 6, 21))
				.isEmpty());
		assertTrue(simpleRepository.findByBirthDateBetween(LocalDate.of(1977, 6, 21), LocalDate.of(1977, 6, 23))
				.isEmpty());

		assertFalse(simpleRepository.findByBirthDateBefore(LocalDate.of(1977, 6, 21)).isEmpty());
		assertTrue(simpleRepository.findByBirthDateBefore(LocalDate.of(1977, 6, 20)).isEmpty());

		assertFalse(simpleRepository.findByBirthDateAfter(LocalDate.of(1977, 6, 19)).isEmpty());
		assertTrue(simpleRepository.findByBirthDateAfter(LocalDate.of(1977, 6, 20)).isEmpty());
	}

	@Test
	public void testFindInteger() {
		assertFalse(simpleRepository.findByAgeLessThan(26).isEmpty());
		assertTrue(simpleRepository.findByAgeLessThan(25).isEmpty());

		assertFalse(simpleRepository.findByAgeLessThanEqual(25).isEmpty());
		assertTrue(simpleRepository.findByAgeLessThanEqual(24).isEmpty());

		assertFalse(simpleRepository.findByAgeGreaterThan(24).isEmpty());
		assertTrue(simpleRepository.findByAgeGreaterThan(25).isEmpty());

		assertFalse(simpleRepository.findByAgeGreaterThanEqual(25).isEmpty());
		assertTrue(simpleRepository.findByAgeGreaterThanEqual(26).isEmpty());

		assertFalse(simpleRepository.findByAgeGreaterThanEqual(25).isEmpty());
		assertTrue(simpleRepository.findByAgeGreaterThanEqual(26).isEmpty());
	}

	@Test
	public void testFindBoolean() {
		assertFalse(simpleRepository.findByRegisteredTrue().isEmpty());
		assertTrue(simpleRepository.findByRegisteredFalse().isEmpty());
	}

	@Test
	public void testFindNamedQuery() {
		assertFalse(simpleRepository.findByLastName("lastName").isEmpty());
		assertTrue(simpleRepository.findByLastName("lastName1").isEmpty());

		assertFalse(simpleRepository.findByLastNameNamedNative("lastName").isEmpty());
		assertTrue(simpleRepository.findByLastNameNamedNative("lastName1").isEmpty());
	}

	@Test
	public void testFindQuery() {
		assertFalse(simpleRepository.findByBirthDate(LocalDate.of(1977, 6, 20)).isEmpty());
		assertTrue(simpleRepository.findByBirthDate(LocalDate.of(1977, 6, 21)).isEmpty());

		assertFalse(simpleRepository.findByLastNameEndsWith("Name").isEmpty());
		assertTrue(simpleRepository.findByLastNameEndsWith("Name1").isEmpty());

		assertFalse(simpleRepository.findByLastNameNative("lastName").isEmpty());
		assertTrue(simpleRepository.findByLastNameNative("lastName1").isEmpty());
	}

	@Test
	public void testSort() {
		Simple simple = createSimple();
		simple.setFirstName("firstName1");
		simpleRepository.save(simple);

		List<Simple> list;

		list = simpleRepository.findAllByOrderByFirstName();
		assertEquals("firstName", list.get(0).getFirstName());

		list = simpleRepository.findAll(Sort.by("firstName").ascending());
		assertEquals("firstName", list.get(0).getFirstName());

		list = simpleRepository.findAll(Sort.by("lastName", "firstName").ascending());
		assertEquals("firstName", list.get(0).getFirstName());

		list = simpleRepository.findAll(Sort.by(Arrays.asList(Order.by("lastName"), Order.asc("firstName"))));
		assertEquals("firstName", list.get(0).getFirstName());

		list = simpleRepository.findAll(Sort.by(Direction.ASC, "lastName", "firstName"));
		assertEquals("firstName", list.get(0).getFirstName());

		// FIXME unsafe not working
//		list = simpleRepository.findAll(JpaSort.unsafe("LENGTH(firstName)"));
//		assertEquals("firstName", list.get(0).getFirstName());

		List<Object> objList;
		objList = simpleRepository.findAllAliasedFunction(Sort.by("fnLength"));
		Object[] objArray = (Object[]) objList.get(0);
		assertEquals("firstName", objArray[1]);

	}

	@Test
	public void testPageable() {
		Simple simple = createSimple();
		simple.setFirstName("firstName1");
		simpleRepository.save(simple);

		// Will not work without Dialect setting
		List<Simple> list;
		list = simpleRepository.findAll(PageRequest.of(1, 1));
		assertEquals(1, list.size());

		list = simpleRepository.findAll(PageRequest.of(2, 1));
		assertEquals(1, list.size());

	}

	@Test
	public void testModifying() {
		int update = simpleRepository.updateFirstNameForLastName("firstName1", "lastName");
		assertEquals(1, update);
		assertEquals("firstName1", simpleRepository.findAll().iterator().next().getFirstName());
	}

}
