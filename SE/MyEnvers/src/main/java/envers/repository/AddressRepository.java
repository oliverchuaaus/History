package envers.repository;

import org.springframework.data.repository.CrudRepository;

import envers.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {

}
