package com.capgemini.chess.generated.entities;
// Generated Aug 24, 2016 8:42:47 AM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.capgemini.chess.algorithms.data.enums.Level;

/**
 * Statistics generated by hbm2java
 */
@Entity
@Table(name = "statistics")
public class StatisticsEntity extends BasicEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int playerPoints;
	private int numberOfMatches;
	private int wonGames;
	private int lostGames;
	private int tiedGames;
	private float winLoseRatio;
	private Level playerLevel;
	private UserEntity user = new UserEntity();

	public StatisticsEntity() {
	}
	
	public StatisticsEntity(int playerPoints, int numberOfMatches, int wonGames, int lostGames, int tiedGames,
			float winLoseRatio, Level playerLevel, UserEntity user) {
		super();
		this.playerPoints = playerPoints;
		this.numberOfMatches = numberOfMatches;
		this.wonGames = wonGames;
		this.lostGames = lostGames;
		this.tiedGames = tiedGames;
		this.winLoseRatio = winLoseRatio;
		this.playerLevel = playerLevel;
		this.user = user;
	}

	@Column(name = "player_points", nullable = false)
	public int getPlayerPoints() {
		return this.playerPoints;
	}

	public void setPlayerPoints(int playerPoints) {
		this.playerPoints = playerPoints;
	}

	@Column(name = "number_of_matches", nullable = false)
	public int getNumberOfMatches() {
		return this.numberOfMatches;
	}

	public void setNumberOfMatches(int numberOfMatches) {
		this.numberOfMatches = numberOfMatches;
	}

	@Column(name = "won_games", nullable = false)
	public int getWonGames() {
		return this.wonGames;
	}

	public void setWonGames(int wonGames) {
		this.wonGames = wonGames;
	}

	@Column(name = "lost_games", nullable = false)
	public int getLostGames() {
		return this.lostGames;
	}

	public void setLostGames(int lostGames) {
		this.lostGames = lostGames;
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

	@Column(name = "player_level", nullable = false, length = 22)
	@Enumerated(EnumType.STRING)
	public Level getPlayerLevel() {
		return this.playerLevel;
	}

	public void setPlayerLevel(Level playerLevel) {
		this.playerLevel = playerLevel;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "statistics")
	public UserEntity getUser() {
		return this.user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}