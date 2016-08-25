package com.capgemini.chess.generated.entities;
// Generated Aug 25, 2016 9:00:51 AM by Hibernate Tools 4.3.1.Final

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Game generated by hbm2java
 */
@Entity
@Table(name = "game")
public class GameEntity extends BasicEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private ChallengeEntity challenge = new ChallengeEntity();

	public GameEntity() {
	}

	public GameEntity(ChallengeEntity challenge) {
		super();
		this.challenge = challenge;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "game")
	public ChallengeEntity getChallenge() {
		return this.challenge;
	}

	public void setChallenge(ChallengeEntity challenge) {
		this.challenge = challenge;
	}

}
