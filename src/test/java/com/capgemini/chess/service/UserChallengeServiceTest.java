package com.capgemini.chess.service;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.ChessApplication;
import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.dataaccess.enums.ChallengeStatus;
import com.capgemini.chess.service.to.ChallengeTo;

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
public class UserChallengeServiceTest {

	/**
	 * Two second in milliseconds.
	 */
	private static int TWO_SECONDS = 20000;

	@Autowired
	private UserChallengeService userChallengeService;

	@Autowired
	private ChallengeDao challengeDao;

	@Before
	public void setUp() {
		challengeDao.getMockingChallengeTableList().clear();
		challengeDao.createListOfChallengeTo();
	}

	/**
	 * Test for invoking accepting a challenge.
	 */
	@Test
	public void shouldAcceptChallenge() {
		// given
		// when
		userChallengeService.acceptChallenge(0);
		// then
		Assert.assertEquals(ChallengeStatus.ACCEPTED, challengeDao.getChallengeById(0).getStatus());
	}

	/**
	 * Test for invoking declining a challenge.
	 */
	@Test
	public void shouldDeclineChallenge() {
		// given
		// when
		userChallengeService.declineChallenge(1);
		// then
		Assert.assertEquals(ChallengeStatus.DECLINED, challengeDao.getChallengeById(1).getStatus());
	}

	/**
	 * Test for invoking creating new challenge.
	 */
	@Test
	public void shouldCreateChallenge() {
		// given
		// when
		userChallengeService.createChallenge(50, 60);
		// then
		Assert.assertEquals(3, challengeDao.getMockingChallengeTableList().size());
		Assert.assertEquals(50, challengeDao.getChallengeById(2).getWhitePlayerId());
		Assert.assertEquals(60, challengeDao.getChallengeById(2).getBlackPlayerId());
	}

	/**
	 * Test for finding every challenge in database.
	 */
	@Test
	public void shouldGetEveryChallengeFromDatabase() {
		// given
		// when
		List<ChallengeTo> testList = userChallengeService.findAllChallenges();
		// then
		Assert.assertEquals(2, testList.size());
	}

	/**
	 * Test for getting every challenge by userID.
	 */
	@Test
	public void shouldGetEveryChallengeOfAUserFromDatabase() {
		// given
		// when
		List<ChallengeTo> testList = userChallengeService.findAllChallengesByUser(1);
		// then
		Assert.assertEquals(1, testList.size());
	}

	/**
	 * Test for getting challenge by its ID.
	 */
	@Test
	public void shouldGetChallengeById() {
		// given
		ChallengeTo testChallenge = new ChallengeTo();
		Date startDate = new Date();
		Date endDate = new Date(startDate.getTime() + TWO_SECONDS);
		// when
		testChallenge = userChallengeService.findChallengeById(0);
		// then
		Assert.assertEquals(testChallenge.getId(), 0);
		Assert.assertEquals(testChallenge.getWhitePlayerId(), 1);
		Assert.assertEquals(testChallenge.getBlackPlayerId(), 2);
		Assert.assertEquals(testChallenge.getStartDate(), startDate);
		Assert.assertEquals(testChallenge.getEndDate(), endDate);
		Assert.assertEquals(testChallenge.getStatus(), ChallengeStatus.WAITING_FOR_REPLY);
	}

	/**
	 * Test for adding new challenge to database.
	 */
	@Test
	public void shouldSaveChallengeIntoDatabase() {
		// given
		Date startDate = new Date();
		Date endDate = new Date(startDate.getTime() + TWO_SECONDS);
		ChallengeTo challengeToSave = new ChallengeTo();
		challengeToSave.setStartDate(startDate);
		challengeToSave.setEndDate(endDate);
		challengeToSave.setWhitePlayerId(23);
		challengeToSave.setBlackPlayerId(123);
		challengeToSave.setStatus(ChallengeStatus.WAITING_FOR_REPLY);
		// when
		userChallengeService.saveChallenge(challengeToSave);
		ChallengeTo challengeFromDatabase = userChallengeService.findChallengeById(2);
		// then
		Assert.assertEquals(challengeToSave, challengeFromDatabase);
	}

	/**
	 * Test for deleting challenge by its ID.
	 */
	@Test
	public void shouldDeleteChallengeById() {
		// given
		int challengeId = 0;
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
