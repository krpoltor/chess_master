package com.capgemini.chess.generated.entities;
// Generated Aug 25, 2016 8:09:37 AM by Hibernate Tools 4.3.1.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.capgemini.chess.dataaccess.enums.ChallengeStatus;

/**
 * Challenge generated by hbm2java
 */
@Entity
@Table(name = "challenge", uniqueConstraints = { @UniqueConstraint(columnNames = "receiver_id"),
		@UniqueConstraint(columnNames = "sender_id") })
public class ChallengeEntity extends BasicEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private PlayerEntity receiver;
	private PlayerEntity sender;
	private Date endDate;
	private Date startDate;
	private ChallengeStatus status;
	private GameEntity game = new GameEntity();

	public ChallengeEntity() {
	}

	public ChallengeEntity(PlayerEntity receiver, PlayerEntity sender, Date endDate, Date startDate,
			ChallengeStatus status, GameEntity game) {
		super();
		this.receiver = receiver;
		this.sender = sender;
		this.endDate = endDate;
		this.startDate = startDate;
		this.status = status;
		this.game = game;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_id", unique = true, nullable = false)
	public PlayerEntity getReceiver() {
		return this.receiver;
	}

	public void setReceiver(PlayerEntity receiver) {
		this.receiver = receiver;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id", unique = true, nullable = false)
	public PlayerEntity getSender() {
		return this.sender;
	}

	public void setSender(PlayerEntity sender) {
		this.sender = sender;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date", nullable = false, length = 19)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date", nullable = false, length = 19)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "status", nullable = false, length = 14)
	@Enumerated(EnumType.STRING)
	public ChallengeStatus getStatus() {
		return this.status;
	}

	public void setStatus(ChallengeStatus status) {
		this.status = status;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "challenge")
	public GameEntity getGame() {
		return this.game;
	}

	public void setGames(GameEntity game) {
		this.game = game;
	}

}
