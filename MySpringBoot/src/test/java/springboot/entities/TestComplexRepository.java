package springboot.entities;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import springboot.entities.complex.League;
import springboot.entities.complex.Player;
import springboot.entities.complex.Team;
import springboot.repositories.complex.LeagueRepository;
import springboot.repositories.complex.PlayerRepository;
import springboot.repositories.complex.TeamRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestComplexRepository {

	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private LeagueRepository leagueRepository;
	@Autowired
	private TeamRepository teamRepository;

	private Player createPlayer() {
		Player p1 = new Player();
		p1.setPlayerName("playerName");
		p1.setSalary(100);
		return p1;
	}

	@Before
	public void init() {
		playerRepository.save(createPlayer());

		League l1 = new League();
		l1.setLeagueName("PBA");
		leagueRepository.save(l1);

		Team t1 = new Team();
		t1.setTeamName("SMB");
		t1.setLeague(l1);
		teamRepository.save(t1);

		List<Team> teamList = new ArrayList<Team>();
		teamList.add(t1);
		Player p2 = new Player();
		p2.setPlayerName("Alan Caidic");
		p2.setSalary(200);
		p2.setTeamList(teamList);
		playerRepository.save(p2);
	}

	@After
	public void tearDown() {
		playerRepository.deleteAll();
		teamRepository.deleteAll();
		leagueRepository.deleteAll();
	}

	@Test
	public void testSimple() {
		List<Player> playerList;
		playerList = playerRepository.findByPlayerNameIgnoreCase("PlayerName");
		assertEquals(1, playerList.size());
	}
	
	@Test
	public void testInnerJoin() {
		List<Player> playerList;
		playerList = playerRepository.findAllInATeam();
		assertEquals(1, playerList.size());
		
		playerList = playerRepository.findByTeamListNotNull();
		assertEquals(1, playerList.size());
	}
	
	@Test
	public void testNavigate() {
		List<Player> playerList;
		String leagueName = "PBA";
		playerList = playerRepository.findAllInLeague(leagueName);
		assertEquals(1, playerList.size());
		
		playerList = playerRepository.findAllInLeague2(leagueName);
		assertEquals(1, playerList.size());
		
		playerList = playerRepository.findByTeamList_League_LeagueName(leagueName);
		assertEquals(1, playerList.size());
	}
	
	@Test
	public void testLeftJoin() {
		List<Player> playerList;
		playerList = playerRepository.findAllWithOrWithoutATeam();
		assertEquals(2, playerList.size());
	}

	@Test
	public void testGroupByHaving() {
		List<Double> averageList;
		averageList = playerRepository.findAverageSalary("PBA");
		assertEquals(200d, averageList.get(0).doubleValue(),0);
	}
}
