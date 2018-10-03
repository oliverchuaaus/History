package envers.repository;

import org.springframework.data.repository.CrudRepository;

import envers.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
