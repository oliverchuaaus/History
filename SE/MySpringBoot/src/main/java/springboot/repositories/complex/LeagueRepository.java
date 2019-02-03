package springboot.repositories.complex;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springboot.entities.complex.League;

@Repository
public interface LeagueRepository extends CrudRepository<League, Long> {
	
}
