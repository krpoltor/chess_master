package com.capgemini.chess.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

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
 * Test class for testing {@link UserChallengeService}<br>
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
@Transactional
public class UserChallengeServiceTest {

	/**
	 * Two second in milliseconds.
	 */
	private static int TWO_SECONDS = 20000;

	@Autowired
	private UserChallengeService userChallengeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	protected EntityManager entityManager;

	/**
	 * Test for invoking accepting a challenge.
	 */
	@Test 
	public void shouldAcceptChallenge() {
		// given
		ChallengeTo challenge = userChallengeService.findChallengeById(1L);
		// when
		userChallengeService.acceptChallenge(challenge);
		// then
		Assert.assertEquals(ChallengeStatus.ACCEPTED, userChallengeService.findChallengeById(1L).getStatus());
	}

	/**
	 * Test for invoking declining a challenge.
	 */
	@Test 
	public void shouldDeclineChallenge() {
		// given
		ChallengeTo challenge = userChallengeService.findChallengeById(1L);
		// when
		userChallengeService.declineChallenge(challenge);
		// then
		Assert.assertEquals(ChallengeStatus.DECLINED, userChallengeService.findChallengeById(1L).getStatus());
	}

	/**
	 * Test for invoking creating new challenge.
	 */
	@Test 
	public void shouldCreateChallenge() {
		// given
		PlayerTo whitePlayer = userService.findUserById(1L);
		PlayerTo blackPlayer = userService.findUserById(2L);
		// when
		ChallengeTo challenge = userChallengeService.createChallenge(whitePlayer, blackPlayer);
		// then
		Assert.assertEquals(whitePlayer, userChallengeService.findChallengeById(4L).getWhitePlayer());
		Assert.assertEquals(blackPlayer, userChallengeService.findChallengeById(4L).getBlackPlayer());
	}

	/**
	 * Test for finding every challenge in database.
	 */
	@Test 
	public void shouldGetEveryChallengeFromDatabase() {
		// given
		// when
		List<ChallengeTo> resultList = userChallengeService.findAllChallenges();
		// then
		Assert.assertEquals(1, resultList.size());
	}

	/**
	 * Test for getting every challenge by userID.
	 */
	@Test 
	public void shouldGetEveryChallengeOfAUserFromDatabase() {
		// given
		// when
		List<ChallengeTo> resultList = userChallengeService.findAllChallengesByUserId(1L);
		// then
		Assert.assertEquals(1, resultList.size());
	}

	/**
	 * Test for getting challenge by its ID.
	 */
	@Test 
	public void shouldGetChallengeById() {
		// given
		// when
		ChallengeTo testChallenge = userChallengeService.findChallengeById(1L);
		// then
		Assert.assertEquals(testChallenge.getId(), new Long(1L));
		Assert.assertEquals(testChallenge.getWhitePlayer(), userService.findUserById(1L));
		Assert.assertEquals(testChallenge.getBlackPlayer(), userService.findUserById(2L));
		Assert.assertEquals(testChallenge.getStatus(), ChallengeStatus.AWAITING_REPLY);
	}

	/**
	 * Test for adding new challenge to database.
	 */
	@Test 
	public void shouldSaveChallengeIntoDatabase() {
		// given
		ChallengeTo challengeToSave = new ChallengeTo();
		Date startDate = new Date();
		challengeToSave.setStartDate(startDate);
		Date endDate = new Date(startDate.getTime() + TWO_SECONDS);
		challengeToSave.setEndDate(endDate);
		PlayerTo whitePlayer = userService.findUserById(1L);
		challengeToSave.setWhitePlayer(whitePlayer);
		PlayerTo blackPlayer = userService.findUserById(2L);
		challengeToSave.setBlackPlayer(blackPlayer);
		challengeToSave.setStatus(ChallengeStatus.AWAITING_REPLY);
		// when
		userChallengeService.saveChallenge(challengeToSave);
		ChallengeTo challengeFromDatabase = userChallengeService.findChallengeById(7L);
		// then
		Assert.assertEquals(challengeToSave, challengeFromDatabase);
	}

	/**
	 * Test for deleting challenge by its ID.
	 */
	@Test 
	public void shouldDeleteChallengeById() {
		// given
		Long challengeId = 1L;
		ChallengeTo challengeBeforeRemoval = userChallengeService.findChallengeById(challengeId);
		// when
		userChallengeService.deleteChallengeById(challengeId);
		// then
		Assert.assertFalse(userChallengeService.findAllChallenges().contains(challengeBeforeRemoval));
	}

	/**
	 * Test for deleting every challenge in database.
	 */
	@Test 
	public void shouldDeleteEveryChallenge() {
		// given
		// when
		userChallengeService.deleteAllChallenges();
		// then
		Assert.assertTrue(userChallengeService.findAllChallenges().isEmpty());
	}

}
