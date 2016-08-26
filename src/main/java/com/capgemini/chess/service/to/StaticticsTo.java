package com.capgemini.chess.service.to;

import com.capgemini.chess.algorithms.data.enums.Level;

public class StaticticsTo extends BasicTo {

	private int lostGames;
	private int numberOfMatches;
	private Level playerLevel;
	private int playerPoints;
	private int tiedGames;
	private float winLoseRatio;
	private int wonGames;

	public StaticticsTo() {
	}

	public StaticticsTo(int lostGames, int numberOfMatches, Level playerLevel, int playerPoints, int tiedGames,
			float winLoseRatio, int wonGames) {
		super();
		this.lostGames = lostGames;
		this.numberOfMatches = numberOfMatches;
		this.playerLevel = playerLevel;
		this.playerPoints = playerPoints;
		this.tiedGames = tiedGames;
		this.winLoseRatio = winLoseRatio;
		this.wonGames = wonGames;
	}

	public int getLostGames() {
		return lostGames;
	}

	public void setLostGames(int lostGames) {
		this.lostGames = lostGames;
	}

	public int getNumberOfMatches() {
		return numberOfMatches;
	}

	public void setNumberOfMatches(int numberOfMatches) {
		this.numberOfMatches = numberOfMatches;
	}

	public Level getPlayerLevel() {
		return playerLevel;
	}

	public void setPlayerLevel(Level playerLevel) {
		this.playerLevel = playerLevel;
	}

	public int getPlayerPoints() {
		return playerPoints;
	}

	public void setPlayerPoints(int playerPoints) {
		this.playerPoints = playerPoints;
	}

	public int getTiedGames() {
		return tiedGames;
	}

	public void setTiedGames(int tiedGames) {
		this.tiedGames = tiedGames;
	}

	public float getWinLoseRatio() {
		return winLoseRatio;
	}

	public void setWinLoseRatio(float winLoseRatio) {
		this.winLoseRatio = winLoseRatio;
	}

	public int getWonGames() {
		return wonGames;
	}

	public void setWonGames(int wonGames) {
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
		StaticticsTo other = (StaticticsTo) obj;
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
