package com.capgemini.chess.rest;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;
import com.capgemini.chess.service.UserChallengeService;
import com.capgemini.chess.service.to.ChallengeTo;

@Controller
@ResponseBody
public class ChallengeRestService {

	private static Logger LOGGER = Logger.getLogger(ChallengeRestService.class.getName());

	@Autowired
	private UserChallengeService userChallengeService;

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
		List<ChallengeTo> allChallenges = userChallengeService.findAllChallenges();
		if (allChallenges.isEmpty()) {
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
	public ResponseEntity<List<ChallengeTo>> getUserChallenges(@PathVariable("userId") int userId) {
		List<ChallengeTo> allUserChallenges = userChallengeService.findAllChallengesByUser(userId);
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
	public ResponseEntity<ChallengeTo> getChallenge(@PathVariable("id") int id) {
		ChallengeTo challenge = userChallengeService.findChallengeById(id);
		if (challenge.equals(null)) {
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
	 * @param ucBuilder
	 * @return headers and HttpStatus.<b>CREATED</b>.
	 */
	@RequestMapping(value = "/rest/challenges", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addChallenge(@RequestBody ChallengeTo challenge, UriComponentsBuilder ucBuilder) {

		if(userChallengeService.findAllChallenges().contains(challenge)){
			LOGGER.info("Challenge: " + challenge.toString() + " already exists!");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		LOGGER.info("Creating challenge: " + challenge.toString());
		userChallengeService.saveChallenge(challenge);
		LOGGER.info("Challenge created");

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/challenges/{id}").buildAndExpand(challenge.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
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
	public ResponseEntity<ChallengeTo> updateChallenge(@PathVariable("id") int id, @RequestBody ChallengeTo challenge) {
		ChallengeTo currentChallenge = userChallengeService.findChallengeById(id);

		if (currentChallenge.equals(null)) {
			LOGGER.info("Challenge with id " + id + " not found");
			return new ResponseEntity<ChallengeTo>(HttpStatus.NOT_FOUND);
		}

		currentChallenge.setWhitePlayerId(challenge.getWhitePlayerId());
		currentChallenge.setBlackPlayerId(challenge.getBlackPlayerId());
		currentChallenge.setStatus(challenge.getStatus());
		currentChallenge.setStartDate(challenge.getStartDate());
		currentChallenge.setEndDate(challenge.getEndDate());
		
		userChallengeService.deleteChallengeById(id);
		userChallengeService.saveChallenge(currentChallenge);
		return new ResponseEntity<ChallengeTo>(currentChallenge, HttpStatus.OK);
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
	public ResponseEntity<ChallengeTo> deleteChallenge(@PathVariable("id") int id) {
		LOGGER.info("Fetching & Deleting User with id " + id);
		ChallengeTo challenge = userChallengeService.findChallengeById(id);
		if (challenge.equals(null)) {
			LOGGER.info("Unable to delete. challenge with id " + id + " not found");
			return new ResponseEntity<ChallengeTo>(HttpStatus.NOT_FOUND);
		}
		userChallengeService.deleteChallengeById(id);
		return new ResponseEntity<ChallengeTo>(HttpStatus.NO_CONTENT);
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

		userChallengeService.deleteAllChallenges();
		return new ResponseEntity<ChallengeTo>(HttpStatus.NO_CONTENT);
	}

}
