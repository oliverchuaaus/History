package springboot.repositories.complex;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springboot.entities.complex.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
	
}
