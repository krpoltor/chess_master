package com.capgemini.chess.rest;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.chess.exceptions.ChallengeNotFoundException;
import com.capgemini.chess.exceptions.UserNotFoundException;
import com.capgemini.chess.service.ChallengeService;
import com.capgemini.chess.service.to.ChallengeTo;

@Controller
@ResponseBody
@Transactional
public class ChallengeRestService {

	private static Logger LOGGER = Logger.getLogger(ChallengeRestService.class.getName());

	@Autowired
	private ChallengeService challengeService;

	/**
	 * Finds all challenges. <br>
	 * Usage: <i>/rest/challenges</i> with RequestMethod.<b>GET</b>
	 * 
	 * @return HttpStatus.<b>NOT_FOUND</b> when database is empty or <br>
	 *         List<ChallengeTo> containing every challenge in database and
	 *         <b>HttpStatus.OK</b>.
	 */
	@RequestMapping(value = "/rest/challenges", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ChallengeTo>> getChallenge() {

		LOGGER.info("Finding every challenge.");

		List<ChallengeTo> allChallenges = challengeService.findAllChallenges();

		if (allChallenges.isEmpty()) {

			LOGGER.info("Challenge table is empty!");

			return new ResponseEntity<List<ChallengeTo>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<ChallengeTo>>(allChallenges, HttpStatus.OK);
	}

	/**
	 * Finds all challenges of the user. <br>
	 * Usage: <i>/rest/challenges/byUser/{userId}</i> with RequestMethod.
	 * <b>GET</b>
	 * 
	 * @param userId
	 *            - ID of a user.
	 * @return HttpStatus.<b>NOT_FOUND</b> when user doesn't have challenges or
	 *         <br>
	 *         List<ChallengeTo> with given user challenges and HttpStatus.
	 *         <b>OK</b>
	 */
	@RequestMapping(value = "/rest/challenges/byUser/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ChallengeTo>> getUserChallenges(@PathVariable("userId") Long userId) {

		LOGGER.info("Finding challenges of user with id: " + userId);

		List<ChallengeTo> allUserChallenges = challengeService.findAllChallengesByUserId(userId);

		if (allUserChallenges.isEmpty()) {

			return new ResponseEntity<List<ChallengeTo>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<ChallengeTo>>(allUserChallenges, HttpStatus.OK);
	}

	/**
	 * Get challenge by ID. <br>
	 * Usage: <i>/rest/challenges/{id}</i> with RequestMethod.<b>GET</b>
	 * 
	 * @param id
	 *            - challenge ID.
	 * @return HttpStatus.<b>NOT_FOUND</b> when this challenge doesn't exist or
	 *         <br>
	 *         found ChallengeTo with HttpStatus.<b>OK</b>.
	 */
	@RequestMapping(value = "/rest/challenges/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ChallengeTo> getChallenge(@PathVariable("id") Long id) {

		ChallengeTo challenge = challengeService.findChallengeById(id);

		if (challenge == null) {

			LOGGER.info("Challenge with id " + id + " not found");

			return new ResponseEntity<ChallengeTo>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ChallengeTo>(challenge, HttpStatus.OK);
	}

	/**
	 * Add new challenge to Database. <br>
	 * Usage: <i>/rest/challenges</i> with RequestMethod.<b>POST</b>
	 * 
	 * @param challenge
	 *            - challenge to add.
	 * @return ChallengeTo and HttpStatus.<b>CREATED</b>.
	 */
	@RequestMapping(value = "/rest/challenges", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ChallengeTo> addChallenge(@RequestBody ChallengeTo challenge)
			throws UserNotFoundException {

		if (challengeService.doesThisChallengeExist(challenge)) {
			LOGGER.warning("Challenge already exists!");
			return new ResponseEntity<ChallengeTo>(HttpStatus.CONFLICT);
		}

		if (challengeService.arePlayersIdTheSame(challenge)) {
			LOGGER.warning("Cannot challenge yourself! (White and black players id's are the same!)");
			return new ResponseEntity<ChallengeTo>(HttpStatus.FORBIDDEN);
		}

		LOGGER.info("Creating challenge: " + challenge.toString());
		challengeService.saveChallenge(challenge);
		LOGGER.info("Challenge created");

		return new ResponseEntity<ChallengeTo>(challenge, HttpStatus.CREATED);
	}

	/**
	 * Update challenge from Database.
	 * 
	 * <br>
	 * Usage: <i>/rest/challenges/{id}</i> with RequestMethod.<b>PUT</b>
	 * 
	 * @param id
	 *            - ID of challenge to update.
	 * @param challenge
	 *            - ChallengeTo with new data
	 * @return HttpStatus.<b>NOT_FOUND</b> when the given challenge couldn't be
	 *         found or <br>
	 *         updated ChallengeTo with HttpStatus.<b>OK</b>.
	 */
	@RequestMapping(value = "/rest/challenges/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ChallengeTo> updateChallenge(@PathVariable("id") Long id, @RequestBody ChallengeTo challenge)
			throws UserNotFoundException, ChallengeNotFoundException {

		if (challengeService.findChallengeById(id) == null) {
			LOGGER.info("Challenge with id " + id + " not found");

			throw new ChallengeNotFoundException();
		}

		if (challengeService.arePlayersIdTheSame(challenge)) {
			LOGGER.warning("Cannot challenge yourself! (White and black players id's are the same!)");

			return new ResponseEntity<ChallengeTo>(HttpStatus.CONFLICT);
		}

		challengeService.updateChallenge(challenge);

		return new ResponseEntity<ChallengeTo>(challenge, HttpStatus.OK);
	}

	/**
	 * Delete challenge by its ID. <br>
	 * Usage: <i>/rest/challenges/{id}</i> with RequestMethod.<b>DELETE</b>
	 * 
	 * @param id
	 *            - ID of challenge to delete.
	 * @return HttpStatus.<b>NOT_FOUND</b> when the challenge to delete couldn't
	 *         be found or<br>
	 *         HttpStatus.<b>NO_CONTENT</b> when challenge was successfully
	 *         deleted.
	 */
	@RequestMapping(value = "/rest/challenges/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ChallengeTo> deleteChallenge(@PathVariable("id") Long id) {

		LOGGER.info("Fetching & Deleting challenge with id " + id);
		ChallengeTo challenge = challengeService.findChallengeById(id);

		if (challenge == null) {
			LOGGER.info("Unable to delete. Challenge with id " + id + " not found");
			return new ResponseEntity<ChallengeTo>(HttpStatus.NOT_FOUND);
		}

		challengeService.deleteChallengeById(id);

		return new ResponseEntity<ChallengeTo>(HttpStatus.OK);
	}

	/**
	 * Delete every challenge.
	 * 
	 * <br>
	 * Usage: <i>/rest/challenges</i> with RequestMethod.<b>DELETE</b>
	 * 
	 * @param id
	 *            - ID of challenge to delete.
	 * @return HttpStatus.<b>NO_CONTENT</b> when challenges were successfully
	 *         deleted.
	 */
	@RequestMapping(value = "/rest/challenges", method = RequestMethod.DELETE)
	public ResponseEntity<ChallengeTo> deleteAllChallenges() {

		LOGGER.info("Deleting every challenge.");

		challengeService.deleteAllChallenges();

		return new ResponseEntity<ChallengeTo>(HttpStatus.OK);
	}

}
