package jpql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "JPQL_LEAGUE")
@SequenceGenerator(name = "LEAGUE_SEQ_STORE", sequenceName = "LEAGUE_SEQ", allocationSize = 50)
public class League {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LEAGUE_SEQ_STORE")
	private Long leagueId;
	private String leagueName;

	public Long getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
}
