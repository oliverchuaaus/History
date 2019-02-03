package springboot.entities.complex;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "JPQL_PLAYER")
@SequenceGenerator(name = "PLAYER_SEQ_STORE", sequenceName = "PLAYER_SEQ", allocationSize = 50)
@NamedQuery(name = "getPlayersNamedLike", query = "SELECT p FROM Player p WHERE UPPER(p.playerName) LIKE :name")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAYER_SEQ_STORE")
	private Long playerId;

	String playerName;

	Integer salary;

	@ManyToMany
	private List<Team> teamList = new ArrayList<Team>();

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public List<Team> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<Team> teamList) {
		this.teamList = teamList;
	}
}
