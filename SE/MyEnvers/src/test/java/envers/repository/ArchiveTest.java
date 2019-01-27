package envers.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import envers.entities.Address;
import envers.entities.ExampleRevEntity;
import envers.entities.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArchiveTest {
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	EntityManagerFactory factory;

	@Test
	public void testArchive() {
		Address address1 = new Address("Privet Drive", 4);
		Person person1 = new Person("Harry", "Potter", address1);
		addressRepository.save(address1);
		personRepository.save(person1);

		Address address2 = new Address("Grimmauld Place", 12);
		Person person2 = new Person("Hermione", "Granger", address2);

		addressRepository.save(address2);
		personRepository.save(person2);

		Optional<Address> address1Opt = addressRepository.findById(address1.getId());
		Optional<Person> person2Opt = personRepository.findById(person2.getId());

		// Changing the address's house number
		address1Opt.orElseThrow().setHouseNumber(5);

		// And moving Hermione to Harry
		person2Opt.orElseThrow().setAddress(address1);

		addressRepository.save(address1Opt.orElseThrow());
		personRepository.save(person2Opt.orElseThrow());

		EntityManager em = factory.createEntityManager();
		AuditQuery query = AuditReaderFactory.get(em).createQuery().forRevisionsOfEntity(Address.class, false,
				false);
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.getResultList();
		for (Object[] object : list) {
			Address address = (Address) object[0];
			ExampleRevEntity exampleRevEntity = (ExampleRevEntity) object[1];
			RevisionType type = (RevisionType) object[2];
			System.out.println(address.getHouseNumber());
			System.out.println(exampleRevEntity.getWhen());
			System.out.println(type);
		}
	}

}
