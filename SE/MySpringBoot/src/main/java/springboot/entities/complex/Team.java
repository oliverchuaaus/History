package springboot.entities.complex;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "JPQL_TEAM")
@SequenceGenerator(name = "TEAM_SEQ_STORE", sequenceName = "TEAM_SEQ", allocationSize = 50)
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEAM_SEQ_STORE")
	private Long teamId;

	private String teamName;
	@ManyToMany(mappedBy = "teamList")
	private List<Player> playerList = new ArrayList<Player>();
	@ManyToOne
	private League league;

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}
}
