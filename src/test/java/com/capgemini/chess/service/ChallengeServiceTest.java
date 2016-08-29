package com.capgemini.chess.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.chess.ChessApplication;
import com.capgemini.chess.dataaccess.enums.ChallengeStatus;
import com.capgemini.chess.service.to.ChallengeTo;
import com.capgemini.chess.service.to.PlayerTo;

/**
 * Test class for testing {@link ChallengeService}<br>
 * 
 * Checklist:<br>
 * 1. Test accepting challenge.<br>
 * 2. Test declining challenge.<br>
 * 3. Test creating new challenge.<br>
 * -//- <br>
 * 4. Test getting every challenge from database.<br>
 * 5. Test getting user challenges.<br>
 * 6. Test adding challenge to database.<br>
 * 7. Test getting challenge by its ID.<br>
 * 8. Test deleting challenge by its ID.<br>
 * 9. Test deleting every challenge in database.<br>
 * 
 * @author KRPOLTOR
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChessApplication.class)
public class ChallengeServiceTest {

	/**
	 * Two second in milliseconds.
	 */
	private static int TWO_SECONDS = 20000;

	@Autowired
	private ChallengeService challengeService;

	@Autowired
	private UserService userService;

	private static Logger LOGGER = Logger.getLogger(ChallengeServiceTest.class.getName());

	/**
	 * Test for invoking accepting a challenge.
	 */
	@Test
	@Transactional(readOnly = false)
	public void shouldAcceptChallenge() {
		// given
		ChallengeTo challenge = challengeService.findChallengeById(1L);
		// when
		challengeService.acceptChallenge(challenge);
		// then
		Assert.assertEquals(ChallengeStatus.ACCEPTED, challengeService.findChallengeById(1L).getStatus());
	}

	/**
	 * Test for invoking declining a challenge.
	 */
	@Test
	@Transactional(readOnly = false)
	public void shouldDeclineChallenge() {
		// given
		ChallengeTo challenge = challengeService.findChallengeById(1L);
		// when
		challengeService.declineChallenge(challenge);
		// then
		Assert.assertEquals(ChallengeStatus.DECLINED, challengeService.findChallengeById(1L).getStatus());
	}

	/**
	 * Test for invoking creating new challenge.
	 */
	@Test
	@Transactional(readOnly = false)
	public void shouldCreateChallenge() {
		// given
		PlayerTo expectedWhitePlayer = userService.findUserById(4L);
		PlayerTo expectedBlackPlayer = userService.findUserById(5L);
		// when
		challengeService.createChallenge(expectedWhitePlayer, expectedBlackPlayer);
		ChallengeTo actualChallenge = challengeService.findChallengeById(3L);
		// then
		Assert.assertEquals(expectedWhitePlayer, actualChallenge.getWhitePlayer());
		Assert.assertEquals(expectedBlackPlayer, actualChallenge.getBlackPlayer());
		Assert.assertEquals(new Long(3L), actualChallenge.getId());
	}

	/**
	 * Test for finding every challenge in database.
	 */
	@Test
	@Transactional(readOnly = false)
	public void shouldGetEveryChallengeFromDatabase() {
		// given
		// when
		List<ChallengeTo> resultList = challengeService.findAllChallenges();
		// then
		Assert.assertEquals(1, resultList.size());
	}

	/**
	 * Test for getting every challenge by userID.
	 */
	@Test
	@Transactional(readOnly = false)
	public void shouldGetEveryChallengeOfAUserFromDatabase() {
		// given
		// when
		List<ChallengeTo> resultList = challengeService.findAllChallengesByUserId(1L);
		// then
		Assert.assertEquals(1, resultList.size());
	}

	/**
	 * Test for getting challenge by its ID.
	 */
	@Test
	@Transactional(readOnly = false)
	public void shouldGetChallengeById() {
		// given
		LOGGER.info("Get challenge by id test.");
		PlayerTo expectedWhitePlayer = userService.findUserById(1L);
		LOGGER.info("Expected white player: " + expectedWhitePlayer);
		PlayerTo expectedBlackPlayer = userService.findUserById(2L);
		LOGGER.info("Expected black player: " + expectedBlackPlayer);
		// when
		ChallengeTo actualChallenge = challengeService.findChallengeById(1L);
		LOGGER.info("Challenge from databse: " + actualChallenge.toString());
		// then
		Assert.assertEquals(new Long(1L), actualChallenge.getId());
		Assert.assertEquals(expectedWhitePlayer, actualChallenge.getWhitePlayer());
		Assert.assertEquals(expectedBlackPlayer, actualChallenge.getBlackPlayer());
		Assert.assertEquals(ChallengeStatus.INIT, actualChallenge.getStatus());
	}

	/**
	 * Test for adding new challenge to database.
	 */
	@Test
	@Transactional(readOnly = false)
	public void shouldSaveChallengeIntoDatabase() {
		// given
		ChallengeTo challengeToToSave = new ChallengeTo();
		Date startDate = new Date();
		challengeToToSave.setStartDate(startDate);
		Date endDate = new Date(startDate.getTime() + TWO_SECONDS);
		challengeToToSave.setEndDate(endDate);
		PlayerTo whitePlayer = userService.findUserById(2L);
		challengeToToSave.setWhitePlayer(whitePlayer);
		PlayerTo blackPlayer = userService.findUserById(3L);
		challengeToToSave.setBlackPlayer(blackPlayer);
		challengeToToSave.setStatus(ChallengeStatus.AWAITING_REPLY);
		// when
		challengeService.saveChallenge(challengeToToSave);
		ChallengeTo challengeFromDatabase = challengeService.findChallengeById(2L);
		challengeToToSave.setId(2L);
		// then
		Assert.assertEquals(challengeToToSave.getId(), challengeFromDatabase.getId());
		Assert.assertEquals(challengeToToSave.getStartDate(), challengeFromDatabase.getStartDate());
		Assert.assertEquals(challengeToToSave.getEndDate(), challengeFromDatabase.getEndDate());
		Assert.assertEquals(challengeToToSave.getWhitePlayer(), challengeFromDatabase.getWhitePlayer());
		Assert.assertEquals(challengeToToSave.getBlackPlayer(), challengeFromDatabase.getBlackPlayer());
		Assert.assertEquals(challengeToToSave.getStatus(), challengeFromDatabase.getStatus());
	}

	/**
	 * Test for deleting challenge by its ID.
	 */
	@Test
	@Transactional(readOnly = false)
	public void shouldDeleteChallengeById() {
		// given
		Long challengeId = 1L;
		ChallengeTo challengeBeforeRemoval = challengeService.findChallengeById(challengeId);
		// when
		challengeService.deleteChallengeById(challengeId);
		// then
		Assert.assertFalse(challengeService.findAllChallenges().contains(challengeBeforeRemoval));
	}

	/**
	 * Test for deleting every challenge in database.
	 */
	@Test
	@Transactional(readOnly = false)
	public void shouldDeleteEveryChallenge() {
		// given
		// when
		challengeService.deleteAllChallenges();
		// then
		Assert.assertTrue(challengeService.findAllChallenges().isEmpty());
	}

}
