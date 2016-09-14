package com.capgemini.chess.dao;

import java.util.List;

import com.capgemini.chess.generated.entities.ChallengeEntity;
import com.capgemini.chess.service.to.ChallengeTo;
import com.capgemini.chess.service.to.PlayerTo;

public interface ChallengeDao extends Dao<ChallengeEntity, Long>{

	/**
	 * Delete overdue challenges.
	 */
	void deleteOverdueChallengesFromListOfChallengeTo();

	/**
	 * Finds challenges of a user.
	 * 
	 * @param userId
	 *            - ID of a user.
	 * @return - List<ChallngeTo> with user's challenges.
	 */
	List<ChallengeEntity> findAllChallengesByUser(String userName);

	/**
	 * Finds all challenges by user id.
	 * @param userId - Id of a user
	 * @return - list of ChallengeEntity
	 */
	List<ChallengeEntity> findAllChallengesByUserId(Long userId);

	/**
	 * Checks if challenge is in database.
	 * @param challenge
	 * @return true when challenge exists in database,<br>
	 * false otherwise
	 */
	boolean doesThisChallengeExist(ChallengeTo challenge);

}
