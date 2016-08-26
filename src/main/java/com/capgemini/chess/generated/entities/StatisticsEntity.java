package com.capgemini.chess.generated.entities;
// Generated Aug 25, 2016 9:00:51 AM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.capgemini.chess.algorithms.data.enums.Level;

/**
 * Statistics generated by hbm2java
 */
@Entity
@Table(name = "statistics")
public class StatisticsEntity extends BasicEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int lostGames;
	private int numberOfMatches;
	private Level playerLevel;
	private int playerPoints;
	private int tiedGames;
	private float winLoseRatio;
	private int wonGames;

	public StatisticsEntity() {
	}

	public StatisticsEntity(int lostGames, int numberOfMatches, Level playerLevel, int playerPoints, int tiedGames,
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

	@Column(name = "lost_games", nullable = false)
	public int getLostGames() {
		return this.lostGames;
	}

	public void setLostGames(int lostGames) {
		this.lostGames = lostGames;
	}

	@Column(name = "number_of_matches", nullable = false)
	public int getNumberOfMatches() {
		return this.numberOfMatches;
	}

	public void setNumberOfMatches(int numberOfMatches) {
		this.numberOfMatches = numberOfMatches;
	}

	@Column(name = "player_level", nullable = false, length = 22)
	@Enumerated(EnumType.STRING)
	public Level getPlayerLevel() {
		return this.playerLevel;
	}

	public void setPlayerLevel(Level playerLevel) {
		this.playerLevel = playerLevel;
	}

	@Column(name = "player_points", nullable = false)
	public int getPlayerPoints() {
		return this.playerPoints;
	}

	public void setPlayerPoints(int playerPoints) {
		this.playerPoints = playerPoints;
	}

	@Column(name = "tied_games", nullable = false)
	public int getTiedGames() {
		return this.tiedGames;
	}

	public void setTiedGames(int tiedGames) {
		this.tiedGames = tiedGames;
	}

	@Column(name = "win_lose_ratio", nullable = false, precision = 12, scale = 0)
	public float getWinLoseRatio() {
		return this.winLoseRatio;
	}

	public void setWinLoseRatio(float winLoseRatio) {
		this.winLoseRatio = winLoseRatio;
	}

	@Column(name = "won_games", nullable = false)
	public int getWonGames() {
		return this.wonGames;
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
		StatisticsEntity other = (StatisticsEntity) obj;
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
		return "StatisticsEntity [lostGames=" + lostGames + ", numberOfMatches=" + numberOfMatches + ", playerLevel="
				+ playerLevel + ", playerPoints=" + playerPoints + ", tiedGames=" + tiedGames + ", winLoseRatio="
				+ winLoseRatio + ", wonGames=" + wonGames + "]";
	}

}
