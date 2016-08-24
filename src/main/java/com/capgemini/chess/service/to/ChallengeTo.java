package com.capgemini.chess.service.to;

import java.util.Date;

import com.capgemini.chess.dataaccess.enums.ChallengeStatus;

public class ChallengeTo {
	
	private Long id;
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

	/**
	 * ChallengeTO parameterized constructor.
	 * 
	 * @param id
	 *            - Challenge's ID.
	 * @param whitePlayer
	 *            - Black set player.
	 * @param blackPlayer
	 *            - White set player.
	 * @param startDate
	 *            - Date of creating a challenge.
	 * @param endDate
	 *            - Date of expiring a challenge.
	 * @param status
	 *            - Status of a challenge from {@link ChallengeStatus}
	 */
	public ChallengeTo(Long id, PlayerTo whitePlayer, PlayerTo blackPlayer, Date startDate, Date endDate,
			ChallengeStatus status) {
		this.id = id;
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blackPlayer == null) ? 0 : blackPlayer.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((whitePlayer == null) ? 0 : whitePlayer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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

	// CHECKSTYLE:OFF
	public Long getId() {
		return id;
	}

	public void setId(Long long1) {
		this.id = long1;
	}

	public PlayerTo getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(PlayerTo user) {
		this.whitePlayer = user;
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

	public ChallengeStatus getStatus() {
		return status;
	}

	public void setStatus(ChallengeStatus status) {
		this.status = status;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	// CHECKSTYLE:ON
}