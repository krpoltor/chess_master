package com.capgemini.chess.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.dataaccess.enums.ChallengeStatus;
import com.capgemini.chess.exceptions.ChallengeNotFoundException;
import com.capgemini.chess.exceptions.UserNotFoundException;
import com.capgemini.chess.generated.entities.ChallengeEntity;
import com.capgemini.chess.service.ChallengeService;
import com.capgemini.chess.service.UserService;
import com.capgemini.chess.service.mapper.ChallengeMapper;
import com.capgemini.chess.service.to.ChallengeTo;
import com.capgemini.chess.service.to.PlayerTo;

@Service
@Transactional
public class ChallengeServiceImpl implements ChallengeService {

	private static int FOURTEEN_DAYS = 14;

	private static Logger LOGGER = Logger.getLogger(ChallengeServiceImpl.class.getName());

	@Autowired
	private ChallengeDao challengeDao;

	@Autowired
	protected EntityManager entityManager;

	@Autowired
	private UserService userService;

	@Override
	public final void acceptChallenge(ChallengeTo challengeTo) {

		ChallengeEntity challengeFromDatabase = null;

		try {
			challengeFromDatabase = challengeDao.getOne(challengeTo.getId());
		} catch (PersistenceException e1) {
			LOGGER.severe("Error in getting challenge from database!");
			e1.printStackTrace();
		}

		challengeTo.setStatus(ChallengeStatus.ACCEPTED);

		LOGGER.info("Setting challenge (" + challengeFromDatabase.toString() + ") status to ACCEPTED.");

		ChallengeEntity entityToSave = ChallengeMapper.update(challengeFromDatabase, challengeTo);

		try {
			challengeDao.update(entityToSave);
		} catch (PersistenceException e) {
			LOGGER.severe("Error in updating challenge!");
			e.printStackTrace();
		}
	}

	@Override
	public final void declineChallenge(ChallengeTo challengeTo) {

		ChallengeEntity challengeFromDatabase = null;

		try {
			challengeFromDatabase = challengeDao.getOne(challengeTo.getId());
		} catch (PersistenceException e1) {
			LOGGER.severe("Error in getting challenge from database!");
			e1.printStackTrace();
		}

		challengeTo.setStatus(ChallengeStatus.DECLINED);

		LOGGER.info("Setting challenge (" + challengeFromDatabase.toString() + ") status to DECLINED.");

		ChallengeEntity entityToSave = ChallengeMapper.update(challengeFromDatabase, challengeTo);

		try {
			challengeDao.update(entityToSave);
		} catch (PersistenceException e) {
			LOGGER.severe("Error in updating challenge!");
			e.printStackTrace();
		}
	}

	@Override
	public ChallengeTo createChallenge(PlayerTo whitePlayerTo, PlayerTo blackPlayerTo) {

		Date startDate = new Date();
		Date endDate = addDaysToGivenDate(startDate, FOURTEEN_DAYS);

		LOGGER.info(
				"Creating new challenge between: " + whitePlayerTo.toString() + "/n and /n" + blackPlayerTo.toString());

		ChallengeTo newChallenge = new ChallengeTo();
		newChallenge.setWhitePlayer(whitePlayerTo);
		newChallenge.setBlackPlayer(blackPlayerTo);
		newChallenge.setStartDate(startDate);
		newChallenge.setEndDate(endDate);
		newChallenge.setStatus(ChallengeStatus.AWAITING_REPLY);

		LOGGER.info("Challenge created.");

		ChallengeEntity newChallengeEntity = ChallengeMapper.map(newChallenge);

		try {
			challengeDao.save(newChallengeEntity);
		} catch (PersistenceException e) {
			LOGGER.severe("Error in inserting challenge into database!");
			e.printStackTrace();
		}

		return newChallenge;
	}

	private Date addDaysToGivenDate(final Date startDate, final int numberOfDaysToAdd) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.DAY_OF_YEAR, numberOfDaysToAdd);

		Date date = calendar.getTime();

		return date;
	}

	@Override
	public List<ChallengeTo> findAllChallenges() {

		List<ChallengeTo> toResultList = null;

		try {
			LOGGER.info("Finding challenges.");
			toResultList = ChallengeMapper.map2TOs(challengeDao.findAll());
			LOGGER.info("Listing challenges.");
		} catch (Exception e) {
			LOGGER.severe("Error in getting every challenge from database!");
			e.printStackTrace();
		}

		return toResultList;
	}

	@Override
	public ChallengeTo findChallengeById(Long id) {

		ChallengeTo resultTo = null;

		try {
			LOGGER.info("Finding challenge by id.");
			resultTo = ChallengeMapper.map(challengeDao.findOne(id));
			LOGGER.info("Listing challenge.");
		} catch (PersistenceException e) {
			LOGGER.severe("Error in finding challenge by id!");
			e.printStackTrace();
		}

		return resultTo;
	}

	@Override
	public void deleteChallengeById(Long id) {

		LOGGER.info("Deleting challenge by id.");
		challengeDao.delete(id);
		LOGGER.info("Challenge deleted.");

	}

	@Override
	public List<ChallengeTo> findAllChallengesByUser(PlayerTo user) {

		List<ChallengeTo> toResultList = null;
		try {
			toResultList = ChallengeMapper.map2TOs(challengeDao.findAllChallengesByUser(user));
		} catch (PersistenceException e) {
			LOGGER.severe("Error in finding user's challenges!");
			e.printStackTrace();
		}

		return toResultList;
	}

	@Override
	public void deleteAllChallenges() {

		challengeDao.deleteAll();
	}

	@Override
	public ChallengeEntity saveChallenge(ChallengeTo challenge) {

		challenge = setPlayers(challenge);

		ChallengeEntity challengeEntity = ChallengeMapper.map(challenge);

		challengeDao.save(challengeEntity);

		return challengeEntity;
	}

	@Override
	public List<ChallengeTo> findAllChallengesByUserId(Long userId) {

		List<ChallengeTo> toResultList = ChallengeMapper.map2TOs(challengeDao.findAllChallengesByUserId(userId));

		return toResultList;
	}

	@Override
	public ChallengeEntity updateChallenge(ChallengeTo challenge) {

		challenge = setPlayers(challenge);

		ChallengeEntity challengeEntityFromDatabase;
		
		try {
			challengeEntityFromDatabase = challengeDao.getOne(challenge.getId());
		} catch (NullPointerException e) {
			throw new ChallengeNotFoundException();
		}

		ChallengeEntity challengeEntity = ChallengeMapper.update(challengeEntityFromDatabase, challenge);

		challengeDao.update(challengeEntity);

		return challengeEntity;
	}

	private ChallengeTo setPlayers(ChallengeTo challenge) {

		PlayerTo sender = userService.findUserById(challenge.getWhitePlayer().getId());
		PlayerTo receiver = userService.findUserById(challenge.getBlackPlayer().getId());

		try {
			if (!sender.equals(null)) {
				challenge.setWhitePlayer(sender);
			}
			if (!receiver.equals(null)) {
				challenge.setBlackPlayer(receiver);
			}
		} catch (NullPointerException e) {
			throw new UserNotFoundException();
		}

		return challenge;
	}

	@Override
	public boolean doesThisChallengeExist(ChallengeTo challenge) {

		return challengeDao.doesThisChallengeExist(challenge);
	}
}
