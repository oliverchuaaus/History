package springboot.repositories.complex;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springboot.entities.complex.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
	public List<Player> findByPlayerNameIgnoreCase(String playerName);

	// Join 2 tables
	@Query("SELECT DISTINCT p FROM Player p, IN(p.teamList) t")
	public List<Player> findAllInATeam();

	@Query("SELECT DISTINCT p FROM Player p JOIN p.teamList t")
	public List<Player> findAllInATeam2();

	public List<Player> findByTeamListNotNull();

	// Join 3 tables
	@Query("SELECT DISTINCT p FROM Player p, IN (p.teamList) t WHERE t.league.leagueName=?1")
	public List<Player> findAllInLeague(String leagueName);

	@Query("SELECT DISTINCT p FROM Player p JOIN p.teamList t JOIN t.league l WHERE l.leagueName=?1")
	public List<Player> findAllInLeague2(String leagueName);

	public List<Player> findByTeamList_League_LeagueName(String leagueName);

	// Left Join
	@Query("SELECT p FROM Player p LEFT JOIN p.teamList t")
	public List<Player> findAllWithOrWithoutATeam();

	@Query("SELECT AVG(p.salary) FROM Player p JOIN p.teamList t GROUP BY t.league.leagueName HAVING t.league.leagueName=?1")
	public List<Double> findAverageSalary(String leagueName);

}
