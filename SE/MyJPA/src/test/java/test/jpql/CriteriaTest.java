package test.jpql;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import jpql.League;
import jpql.League_;
import jpql.Player;
import jpql.PlayerDTO;
import jpql.Player_;
import jpql.Team;
import jpql.Team_;

import org.junit.BeforeClass;
import org.junit.Test;

import util.JPAUtil;

public class CriteriaTest {

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
		/*
		 * "SELECT p FROM Player p WHERE UPPER(p.playerName) LIKE :name";
		 */
		EntityManager em = JPAUtil.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Player> cq = cb.createQuery(Player.class);
		Root<Player> player = cq.from(Player.class);
		cq.where(cb.like(cb.upper(player.get(Player_.playerName)), "PLAYER%"));
		cq.select(player);
		List<Player> playerList = em.createQuery(cq).getResultList();
		assertEquals(1, playerList.size());
	}

	@Test
	public void testInnerJoin() {
		/*
		 * "SELECT DISTINCT p FROM Player p, IN(p.teamList) t";
		 */
		EntityManager em = JPAUtil.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Player> cq = cb.createQuery(Player.class);
		Root<Player> player = cq.from(Player.class);
		player.join(Player_.teamList);
		cq.where(cb.like(cb.upper(player.get(Player_.playerName)), "ALAN%"));
		cq.select(player);
		List<Player> playerList = em.createQuery(cq).getResultList();
		assertEquals(1, playerList.size());
	}

	@Test
	public void testNavigateSet() {
		// Players of a League
		// Using class as parameter

		/*
		 * "SELECT DISTINCT p FROM Player p, IN (p.teamList) t WHERE t.league.leagueName=:league"
		 */
		EntityManager em = JPAUtil.getEm();

		List<League> leagueList = em
				.createQuery(
						"SELECT l FROM League l WHERE l.leagueName=:leagueName",
						League.class).setParameter("leagueName", "PBA")
				.getResultList();
		assertEquals(1, leagueList.size());
		League league = leagueList.get(0);

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Player> cq = cb.createQuery(Player.class);
		Root<Player> player = cq.from(Player.class);
		Join<Player, Team> team = player.join(Player_.teamList);
		cq.where(cb.equal(team.get(Team_.league), league));
		cq.select(player);
		List<Player> playerList = em.createQuery(cq).getResultList();
		assertEquals(1, playerList.size());

		cb = em.getCriteriaBuilder();
		cq = cb.createQuery(Player.class);
		player = cq.from(Player.class);
		team = player.join(Player_.teamList);
		Join<Team, League> leagueJoin = team.join(Team_.league);

		cq.where(cb.equal(leagueJoin.get(League_.leagueName),
				league.getLeagueName()));
		cq.select(player);
		playerList = em.createQuery(cq).getResultList();
		assertEquals(1, playerList.size());
	}

	// Not possible
	// public void testLeftJoin() {

	// Not possible
	// public void testFetchJoin() {

	@Test
	public void testSubQueries() {
		// EXISTS
		/*
		 * "SELECT p FROM Player p WHERE NOT EXISTS (SELECT l FROM League l WHERE l.leagueName='NBA')"
		 * ;
		 */
		EntityManager em = JPAUtil.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Player> cq = cb.createQuery(Player.class);
		Root<Player> player = cq.from(Player.class);

		Subquery<League> subquery = cq.subquery(League.class);
		Root<League> league = subquery.from(League.class);

		List<Predicate> subQueryPredicates = new ArrayList<Predicate>();
		subQueryPredicates.add(cb.equal(league.get(League_.leagueName), "NBA"));
		subquery.where(subQueryPredicates.toArray(new Predicate[] {}));
		subquery.select(league);

		cq.where(cb.not(cb.exists(subquery)));
		cq.select(player);
		List<Player> playerList = em.createQuery(cq).getResultList();
		assertEquals(2, playerList.size());

		// ALL
		/*
		 * "SELECT p FROM Player p WHERE p.salary <= ALL (SELECT p.salary FROM Player p)"
		 */

		em = JPAUtil.getEm();
		cb = em.getCriteriaBuilder();
		cq = cb.createQuery(Player.class);
		player = cq.from(Player.class);

		Subquery<Integer> subqueryInteger = cq.subquery(Integer.class);
		Root<Player> playerSubquery = subqueryInteger.from(Player.class);
		subQueryPredicates = new ArrayList<Predicate>();
		subQueryPredicates
				.add(cb.isNotNull(playerSubquery.get(Player_.salary)));
		subqueryInteger.where(subQueryPredicates.toArray(new Predicate[] {}));
		subqueryInteger.select(playerSubquery.get(Player_.salary));

		cq.where(cb.le(player.get(Player_.salary), cb.all(subqueryInteger)));
		cq.select(player);
		playerList = em.createQuery(cq).getResultList();
		assertEquals(1, playerList.size());
		assertEquals(100, playerList.get(0).getSalary().intValue());

		// IN
		/*
		 * "SELECT p FROM Player p, IN(p.teamList) t WHERE t.league.leagueName IN (?1)"
		 * ;
		 */
		cb = em.getCriteriaBuilder();
		cq = cb.createQuery(Player.class);
		player = cq.from(Player.class);
		Join<Team, League> leagueJoin = player.join(Player_.teamList).join(
				Team_.league);
		cq.where((leagueJoin.get(League_.leagueName).in(Arrays.asList("PBA",
				"NBA"))));
		cq.select(player);
		playerList = em.createQuery(cq).getResultList();
		assertEquals(1, playerList.size());
		assertEquals("Alan Caidic", playerList.get(0).getPlayerName());
	}

	@Test
	public void testGroupBy() {
		// EXISTS
		EntityManager em = JPAUtil.getEm();

		/*
		 * "SELECT AVG(p.salary) FROM Player p, IN(p.teamList) t GROUP BY t.league.leagueName HAVING t.league.leagueName='PBA'"
		 * ;
		 */

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Double> cq = cb.createQuery(Double.class);
		Root<Player> player = cq.from(Player.class);
		Join<Player, Team> team = player.join(Player_.teamList);
		Join<Team, League> leagueJoin = team.join(Team_.league);
		cq.groupBy(leagueJoin.get(League_.leagueName)).having(
				cb.equal(leagueJoin.get(League_.leagueName), "PBA"));
		cq.select(cb.avg(player.get(Player_.salary)));
		List<Double> playerSalaryList = em.createQuery(cq).getResultList();
		assertEquals(1, playerSalaryList.size());
		assertEquals(200, playerSalaryList.get(0).intValue());
	}

	@Test
	public void testDTO() {
		// Need fully qualified name
		/*
		 * "SELECT new jpql.PlayerDTO(p.playerId, p.playerName, p.salary) FROM Player p, IN(p.teamList) t"
		 * ;
		 */

		EntityManager em = JPAUtil.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PlayerDTO> cq = cb.createQuery(PlayerDTO.class);
		Root<Player> player = cq.from(Player.class);
		player.join(Player_.teamList);
		cq.where(cb.like(cb.upper(player.get(Player_.playerName)), "ALAN%"));
		cq.select(cb.construct(PlayerDTO.class, player.get(Player_.playerId),
				player.get(Player_.playerName), player.get(Player_.salary)));
		List<PlayerDTO> tupleList = em.createQuery(cq).getResultList();
		assertEquals(1, tupleList.size());
		assertEquals("Alan Caidic", tupleList.get(0).getPlayerName());
		assertEquals(200, tupleList.get(0).getSalary().intValue());

	}

	@Test
	public void testLiterals() {
		// Need fully qualified name
		/*
		 * "SELECT p.playerId, p.playerName, p.salary FROM Player p, IN(p.teamList) t"
		 * ;
		 */
		EntityManager em = JPAUtil.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
		Root<Player> player = cq.from(Player.class);
		player.join(Player_.teamList);
		cq.where(cb.like(cb.upper(player.get(Player_.playerName)), "ALAN%"));
		cq.select(cb.array(player.get(Player_.playerId),
				player.get(Player_.playerName), player.get(Player_.salary)));
		List<Object[]> tupleList = em.createQuery(cq).getResultList();
		assertEquals(1, tupleList.size());
		assertEquals("Alan Caidic", tupleList.get(0)[1]);
		assertEquals(200, tupleList.get(0)[2]);
	}

	@Test
	public void testObjects() {
		/*
		 * "SELECT p, t FROM Player p, IN(p.teamList) t";
		 */
		EntityManager em = JPAUtil.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
		Root<Player> player = cq.from(Player.class);
		Join<Player, Team> team = player.join(Player_.teamList);
		cq.where(cb.like(cb.upper(player.get(Player_.playerName)), "ALAN%"));
		cq.select(cb.array(player, team));
		List<Object[]> tupleList = em.createQuery(cq).getResultList();
		assertEquals(1, tupleList.size());
		Player p = (Player) tupleList.get(0)[0];
		Team t = (Team) tupleList.get(0)[1];
		assertEquals("Alan Caidic", p.getPlayerName());
		assertEquals("SMB", t.getTeamName());

	}

	@Test
	public void testPagination() {
		/* "SELECT p FROM Player p ORDER BY p.playerId"; */

		EntityManager em = JPAUtil.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Player> cq = cb.createQuery(Player.class);
		Root<Player> player = cq.from(Player.class);
		cq.orderBy(cb.asc(player.get(Player_.playerId)));
		cq.select(player);

		List<Player> playerList = em.createQuery(cq).getResultList();
		assertEquals(2, playerList.size());

		playerList = em.createQuery(cq).setFirstResult(0).setMaxResults(1)
				.getResultList();
		assertEquals(1, playerList.size());
		assertEquals("playerName", playerList.get(0).getPlayerName());

		playerList = em.createQuery(cq).setFirstResult(1).setMaxResults(1)
				.getResultList();
		assertEquals(1, playerList.size());
		assertEquals("Alan Caidic", playerList.get(0).getPlayerName());
	}
}
