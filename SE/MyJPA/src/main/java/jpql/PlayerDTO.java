package jpql;

public class PlayerDTO {
	private Long playerId;
	private String playerName;
	private Integer salary;

	public PlayerDTO(Long playerId, String playerName, Integer salary) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.salary = salary;
	}

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

}
