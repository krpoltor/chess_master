package com.capgemini.chess.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.chess.generated.entities.ChallengeEntity;
import com.capgemini.chess.service.to.ChallengeTo;
import com.capgemini.chess.service.to.PlayerTo;

/**
 * @author KRPOLTOR
 *
 */
@Service
public interface ChallengeService {

	/**
	 * Change status of given challenge to ACCEPTED.
	 * 
	 * @param challenge
	 *            - ChallengeTo.
	 */
	void acceptChallenge(ChallengeTo challenge);

	/**
	 * Declining a challenge invokes removing it from DB.
	 * 
	 * @param challenge
	 *            - ChallengeTo.
	 */
	void declineChallenge(ChallengeTo challenge);

	/**
	 * Create a new challenge between two players and set it's status to
	 * AWAITING_REPLY.
	 * 
	 * @param whitePlayer
	 *            - TO of a player playing with white chess-set.
	 * @param blackPlayer
	 *            - TO of a player playing with black chess-set.
	 */
	ChallengeTo createChallenge(PlayerTo whitePlayer, PlayerTo blackPlayer);

	/**
	 * Finds all challenges in database.
	 * 
	 * @return List<ChallengeTo> with every challenge from Database.
	 */
	List<ChallengeTo> findAllChallenges();

	/**
	 * Finds all challenges of the user.
	 * 
	 * @param user
	 *            - User.
	 * @return List<ChallengeTo> with every challenge of the user.
	 */
	List<ChallengeTo> findAllChallengesByUser(PlayerTo user);

	/**
	 * Finds challenge by its ID.
	 * 
	 * @param id
	 *            - ID of a challenge.
	 * @return
	 */
	ChallengeTo findChallengeById(Long id);

	/**
	 * Saves ChallengeTo in database.
	 * 
	 * @param challenge
	 *            - challenge to save.
	 * @return ChallengeEntity
	 */
	ChallengeEntity saveChallenge(ChallengeTo challenge);

	/**
	 * Deletes challenge with given ID.
	 * 
	 * @param id
	 *            - ID of a challenge to remove.
	 */
	void deleteChallengeById(Long id);

	/**
	 * Deletes every challenge in database.
	 */
	void deleteAllChallenges();

	/**
	 * Finds all user challenges.
	 * @param userId - Id of a user
	 * @return - list of ChallengeTo
	 */
	List<ChallengeTo> findAllChallengesByUserId(Long userId);
	
	/**
	 * Change values of challenge.
	 * 
	 * @param challenge
	 *            - challenge to update.
	 * @return ChallengeTo
	 */
	ChallengeTo updateChallenge(ChallengeTo challenge);

	/**
	 * Checks if challenge is in database.
	 * @param challenge
	 * @return true when challenge exists in database,<br>
	 * false otherwise
	 */
	boolean doesThisChallengeExist(ChallengeTo challenge);

	/**
	 * Checks if players id's are the same.
	 * @param challenge
	 * @return true when they are,<br>
	 * false otherwise
	 */
	boolean arePlayersIdTheSame(ChallengeTo challenge);

}
