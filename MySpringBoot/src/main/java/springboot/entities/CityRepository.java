package springboot.entities;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
	
	public List<City> findByName(String name);
	
	public List<City> findByName(String name, Pageable pageable);
}
