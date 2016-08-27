package com.capgemini.chess.service.to;

import com.capgemini.chess.algorithms.data.enums.Level;

public class StatisticsTo extends BasicTo {

	private Integer lostGames;
	private Integer numberOfMatches;
	private Level playerLevel;
	private Integer playerPoints;
	private Integer tiedGames;
	private Float winLoseRatio;
	private Integer wonGames;

	public StatisticsTo() {
	}

	public StatisticsTo(Integer lostGames, Integer numberOfMatches, Level playerLevel, Integer playerPoints, Integer tiedGames,
			Float winLoseRatio, Integer wonGames) {
		super();
		this.lostGames = lostGames;
		this.numberOfMatches = numberOfMatches;
		this.playerLevel = playerLevel;
		this.playerPoints = playerPoints;
		this.tiedGames = tiedGames;
		this.winLoseRatio = winLoseRatio;
		this.wonGames = wonGames;
	}

	public Integer getLostGames() {
		return lostGames;
	}

	public void setLostGames(Integer lostGames) {
		this.lostGames = lostGames;
	}

	public Integer getNumberOfMatches() {
		return numberOfMatches;
	}

	public void setNumberOfMatches(Integer numberOfMatches) {
		this.numberOfMatches = numberOfMatches;
	}

	public Level getPlayerLevel() {
		return playerLevel;
	}

	public void setPlayerLevel(Level playerLevel) {
		this.playerLevel = playerLevel;
	}

	public Integer getPlayerPoints() {
		return playerPoints;
	}

	public void setPlayerPoints(Integer playerPoints) {
		this.playerPoints = playerPoints;
	}

	public Integer getTiedGames() {
		return tiedGames;
	}

	public void setTiedGames(Integer tiedGames) {
		this.tiedGames = tiedGames;
	}

	public Float getWinLoseRatio() {
		return winLoseRatio;
	}

	public void setWinLoseRatio(Float winLoseRatio) {
		this.winLoseRatio = winLoseRatio;
	}

	public Integer getWonGames() {
		return wonGames;
	}

	public void setWonGames(Integer wonGames) {
		this.wonGames = wonGames;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + lostGames;
		result = prime * result + numberOfMatches;
		result = prime * result + ((playerLevel == null) ? 0 : playerLevel.hashCode());
		result = prime * result + playerPoints;
		result = prime * result + tiedGames;
		result = prime * result + Float.floatToIntBits(winLoseRatio);
		result = prime * result + wonGames;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatisticsTo other = (StatisticsTo) obj;
		if (lostGames != other.lostGames)
			return false;
		if (numberOfMatches != other.numberOfMatches)
			return false;
		if (playerLevel != other.playerLevel)
			return false;
		if (playerPoints != other.playerPoints)
			return false;
		if (tiedGames != other.tiedGames)
			return false;
		if (Float.floatToIntBits(winLoseRatio) != Float.floatToIntBits(other.winLoseRatio))
			return false;
		if (wonGames != other.wonGames)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StaticticsTo [lostGames=" + lostGames + ", numberOfMatches=" + numberOfMatches + ", playerLevel="
				+ playerLevel + ", playerPoints=" + playerPoints + ", tiedGames=" + tiedGames + ", winLoseRatio="
				+ winLoseRatio + ", wonGames=" + wonGames + "]";
	}

}
