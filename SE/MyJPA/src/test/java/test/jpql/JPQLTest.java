package test.jpql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import jpql.League;
import jpql.Player;
import jpql.PlayerDTO;
import jpql.Team;

import org.junit.BeforeClass;
import org.junit.Test;

import util.JPAUtil;

public class JPQLTest {

	@BeforeClass
	public static void set() throws Exception {
		Player p1 = new Player();
		p1.setPlayerName("playerName");
		p1.setSalary(100);

		EntityManager em = JPAUtil.getEm();

		// Clear tables
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM JPQL_PLAYER_JPQL_TEAM").executeUpdate();
		em.createNativeQuery("DELETE FROM JPQL_TEAM").executeUpdate();
		em.createNativeQuery("DELETE FROM JPQL_LEAGUE").executeUpdate();
		em.createNativeQuery("DELETE FROM JPQL_PLAYER").executeUpdate();

		League l1 = new League();
		l1.setLeagueName("PBA");

		Team t1 = new Team();
		t1.setTeamName("SMB");
		t1.setLeague(l1);

		List<Team> teamList = new ArrayList<Team>();
		teamList.add(t1);
		Player p2 = new Player();
		p2.setPlayerName("Alan Caidic");
		p2.setSalary(200);
		p2.setTeamList(teamList);

		em.persist(p1);
		em.persist(l1);
		em.persist(t1);
		em.persist(p2);

		em.getTransaction().commit();
	}

	@Test
	public void testCreateQuery() {
		String jpql = "SELECT p FROM Player p WHERE UPPER(p.playerName) LIKE :name";
		EntityManager em = JPAUtil.getEm();
		List<Player> playerList = em.createQuery(jpql, Player.class)
				.setParameter("name", "PLAYER%").getResultList();
		assertEquals(1, playerList.size());
	}

	@Test
	public void testCreateQueryPositional() {
		String jpql = "SELECT p FROM Player p WHERE UPPER(p.playerName) LIKE ?1";
		EntityManager em = JPAUtil.getEm();
		List<Player> playerList = em.createQuery(jpql, Player.class)
				.setParameter(1, "PLAYER%").getResultList();
		assertEquals(1, playerList.size());
	}

	@Test
	public void testNamedQuery() {
		EntityManager em = JPAUtil.getEm();
		List<Player> playerList = em
				.createNamedQuery("getPlayersNamedLike", Player.class)
				.setParameter("name", "PLAYER%").getResultList();
		assertEquals(1, playerList.size());
	}

	@Test
	public void testInnerJoin() {
		// Players with teams
		EntityManager em = JPAUtil.getEm();
		String jpql = "SELECT DISTINCT p FROM Player p, IN(p.teamList) t";
		List<Player> playerList = em.createQuery(jpql, Player.class)
				.getResultList();
		assertEquals(1, playerList.size());

		// same result
		jpql = "SELECT DISTINCT p FROM Player p JOIN p.teamList t";
		playerList = em.createQuery(jpql, Player.class).getResultList();
		assertEquals(1, playerList.size());

		// same result
		jpql = "SELECT DISTINCT p FROM Player p WHERE p.teamList IS NOT EMPTY";
		playerList = em.createQuery(jpql, Player.class).getResultList();
		assertEquals(1, playerList.size());
	}

	@Test
	public void testNavigateSet() {
		// Players of a League
		// Using class as parameter
		EntityManager em = JPAUtil.getEm();

		String jpql = "SELECT DISTINCT p FROM Player p, IN (p.teamList) t WHERE t.league.leagueName=:league";
		List<Player> playerList = em.createQuery(jpql, Player.class)
				.setParameter("league", "PBA").getResultList();
		assertEquals(1, playerList.size());

		// same result
		List<League> leagueList = em
				.createQuery(
						"SELECT l FROM League l WHERE l.leagueName=:leagueName",
						League.class).setParameter("leagueName", "PBA")
				.getResultList();
		assertEquals(1, leagueList.size());
		League league = leagueList.get(0);
		jpql = "SELECT DISTINCT p FROM Player p, IN (p.teamList) t WHERE t.league=:league";
		playerList = em.createQuery(jpql, Player.class)
				.setParameter("league", league).getResultList();
		assertEquals(1, playerList.size());
	}

	@Test
	public void testBadSetNavigate() {
		// Get all players with or without teams
		EntityManager em = JPAUtil.getEm();

		try {
			String jpql = "SELECT p FROM Player p WHERE p.teamList.name=?1";
			em.createQuery(jpql, Player.class).setParameter(1, "PBA")
					.getResultList();
		} catch (IllegalArgumentException se) {
			assertTrue(se.getMessage().contains(
					"could not resolve property: name of: jpql.Team"));
		}
	}

	@Test
	public void testLeftJoin() {
		// Get all players with or without teams
		EntityManager em = JPAUtil.getEm();

		String jpql = "SELECT p FROM Player p LEFT JOIN p.teamList t";
		List<Player> playerList = em.createQuery(jpql, Player.class)
				.getResultList();
		assertEquals(2, playerList.size());
	}

	@Test
	public void testFetchJoin() {
		// Get all players with or without teams
		EntityManager em = JPAUtil.getEm();

		String jpql = "SELECT p FROM Player p LEFT JOIN FETCH p.teamList t";
		List<Player> playerList = em.createQuery(jpql, Player.class)
				.getResultList();
		assertEquals(2, playerList.size());
		// SQL:
		// select * from JPQL_PLAYER player0_
		// left outer join JPQL_PLAYER_JPQL_TEAM teamlist1_ on
		// player0_.playerId=teamlist1_.playerList_playerId
		// left outer join JPQL_TEAM team2_ on
		// teamlist1_.teamList_teamId=team2_.teamId

		Player p = playerList.get(0);
		Team t = p.getTeamList().get(0);
		assertEquals("SMB", t.getTeamName());
	}

	@Test
	public void testSubQueries() {
		// EXISTS
		EntityManager em = JPAUtil.getEm();

		String jpql = "SELECT p FROM Player p WHERE NOT EXISTS (SELECT l FROM League l WHERE l.leagueName='NBA')";
		List<Player> playerList = em.createQuery(jpql, Player.class)
				.getResultList();
		assertEquals(2, playerList.size());
		// ALL
		jpql = "SELECT p FROM Player p WHERE p.salary <= ALL (SELECT p.salary FROM Player p)";
		playerList = em.createQuery(jpql, Player.class).getResultList();
		assertEquals(1, playerList.size());
		assertEquals(100, playerList.get(0).getSalary().intValue());
		// ANY
		jpql = "SELECT p FROM Player p WHERE p.salary < ANY (SELECT p.salary FROM Player p, IN (p.teamList) t WHERE t.league.leagueName='PBA')";
		playerList = em.createQuery(jpql, Player.class).getResultList();
		assertEquals(1, playerList.size());
		assertEquals(100, playerList.get(0).getSalary().intValue());
		// IN
		jpql = "SELECT p FROM Player p, IN(p.teamList) t WHERE t.league.leagueName IN (?1)";
		playerList = em.createQuery(jpql, Player.class)
				.setParameter(1, Arrays.asList("PBA","NBA")).getResultList();
		assertEquals(1, playerList.size());
		assertEquals("Alan Caidic", playerList.get(0).getPlayerName());
	}

	@Test
	public void testGroupBy() {
		// EXISTS
		EntityManager em = JPAUtil.getEm();

		String jpql = "SELECT AVG(p.salary) FROM Player p, IN(p.teamList) t GROUP BY t.league.leagueName HAVING t.league.leagueName='PBA'";
		List<Double> averageSalary = em.createQuery(jpql, Double.class)
				.getResultList();
		assertEquals(200, averageSalary.get(0).intValue());
	}

	@Test
	public void testDTO() {
		// Need fully qualified name
		EntityManager em = JPAUtil.getEm();
		String jpql = "SELECT new jpql.PlayerDTO(p.playerId, p.playerName, p.salary) FROM Player p, IN(p.teamList) t";
		List<PlayerDTO> playerList = em.createQuery(jpql, PlayerDTO.class)
				.getResultList();
		assertEquals(1, playerList.size());
	}

	@Test
	public void testLiterals() {
		// Need fully qualified name
		EntityManager em = JPAUtil.getEm();
		String jpql = "SELECT p.playerId, p.playerName, p.salary FROM Player p, IN(p.teamList) t";
		List<Object[]> playerList = em.createQuery(jpql, Object[].class)
				.getResultList();
		assertEquals(1, playerList.size());
		assertEquals("Alan Caidic", playerList.get(0)[1]);
	}

	@Test
	public void testObjects() {
		EntityManager em = JPAUtil.getEm();
		String jpql = "SELECT p, t FROM Player p, IN(p.teamList) t";
		List<Object[]> tupleList = em.createQuery(jpql, Object[].class)
				.getResultList();
		assertEquals(1, tupleList.size());
		Player p = (Player) tupleList.get(0)[0];
		Team t = (Team) tupleList.get(0)[1];
		assertEquals("Alan Caidic", p.getPlayerName());
		assertEquals("SMB", t.getTeamName());
	}

	@Test
	public void testPagination() {
		EntityManager em = JPAUtil.getEm();
		String jpql = "SELECT p FROM Player p ORDER BY p.playerId";
		List<Player> playerList = em.createQuery(jpql, Player.class)
				.getResultList();
		assertEquals(2, playerList.size());

		playerList = em.createQuery(jpql, Player.class).setFirstResult(0)
				.setMaxResults(1).getResultList();
		assertEquals(1, playerList.size());
		assertEquals("playerName", playerList.get(0).getPlayerName());

		playerList = em.createQuery(jpql, Player.class).setFirstResult(1)
				.setMaxResults(1).getResultList();
		assertEquals(1, playerList.size());
		assertEquals("Alan Caidic", playerList.get(0).getPlayerName());
	}
}
