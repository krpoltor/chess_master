package com.capgemini.chess.service.to;

import java.util.Date;

import com.capgemini.chess.dataaccess.enums.ChallengeStatus;

public class ChallengeTo extends BasicTo {

	private GameTo game;
	private PlayerTo whitePlayer;
	private PlayerTo blackPlayer;
	private Date startDate;
	private Date endDate;
	private ChallengeStatus status;

	/**
	 * ChallengeTO default constructor.
	 */
	public ChallengeTo() {
	}

	public ChallengeTo(GameTo game, PlayerTo whitePlayer, PlayerTo blackPlayer, Date startDate, Date endDate,
			ChallengeStatus status) {
		super();
		this.game = game;
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public GameTo getGame() {
		return game;
	}

	public void setGame(GameTo game) {
		this.game = game;
	}

	public PlayerTo getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(PlayerTo whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	public PlayerTo getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(PlayerTo blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ChallengeStatus getStatus() {
		return status;
	}

	public void setStatus(ChallengeStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((blackPlayer == null) ? 0 : blackPlayer.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((whitePlayer == null) ? 0 : whitePlayer.hashCode());
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
		ChallengeTo other = (ChallengeTo) obj;
		if (blackPlayer == null) {
			if (other.blackPlayer != null)
				return false;
		} else if (!blackPlayer.equals(other.blackPlayer))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status != other.status)
			return false;
		if (whitePlayer == null) {
			if (other.whitePlayer != null)
				return false;
		} else if (!whitePlayer.equals(other.whitePlayer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChallengeTo [game=" + game + ", whitePlayer=" + whitePlayer + ", blackPlayer=" + blackPlayer
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + "]";
	}

}