package com.capgemini.chess.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.capgemini.chess.service.to.ChallengeTo;

/**
 * @author KRPOLTOR
 *
 */
@Service
public interface UserChallengeService {

	/**
	 * Change status of given challenge to ACCEPTED.
	 * 
	 * @param challengeId
	 *            - Challenge ID.
	 */
	void acceptChallenge(int challengeId);

	/**
	 * Declining a challenge invokes removing it from DB.
	 * 
	 * @param challengeId
	 *            - Challenge ID.
	 */
	void declineChallenge(int challengeId);

	/**
	 * Create a new challenge between two players and set it's status to
	 * WAITING_FOR_REPLY.
	 * 
	 * @param whitePlayerId
	 *            - ID of a player playing with white chess-set.
	 * @param blackPlayerId
	 *            - ID of a player playing with black chess-set.
	 */
	void createChallenge(int whitePlayerId, int blackPlayerId);

	// zwracanie challeneTO ktory zapisuje w bazie danych
	// komentarz do klasy

	/**
	 * Finds all challenges in database.
	 * 
	 * @return List<ChallengeTo> with every challenge from Database.
	 */
	List<ChallengeTo> findAllChallenges();

	/**
	 * Finds all challenges of the user.
	 * 
	 * @param userId
	 *            - ID of user.
	 * @return List<ChallengeTo> with every challenge of the user.
	 */
	List<ChallengeTo> findAllChallengesByUser(int userId);

	/**
	 * Finds challenge by its ID.
	 * 
	 * @param id
	 *            - ID of a challenge.
	 * @return
	 */
	ChallengeTo findChallengeById(int id);

	/**
	 * Saves ChallengeTo in database.
	 * 
	 * @param challenge
	 *            - challenge to save.
	 */
	void saveChallenge(ChallengeTo challenge);

	/**
	 * Deletes challenge with given ID.
	 * 
	 * @param id
	 *            - ID of a challenge to remove.
	 */
	void deleteChallengeById(int id);

	/**
	 * Deletes every challenge in database.
	 */
	void deleteAllChallenges();

}
