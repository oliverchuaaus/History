package springboot.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springboot.entities.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
	
	public List<City> findByName(String name);
	
	public List<City> findByName(String name, Pageable pageable);
}
