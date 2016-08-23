package com.capgemini.chess.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.dataaccess.enums.ChallengeStatus;
import com.capgemini.chess.service.UserChallengeService;
import com.capgemini.chess.service.to.ChallengeTo;

@Service
public class UserChallengeServiceImpl implements UserChallengeService {

	private static int FOURTEEN_DAYS = 14;

	@Autowired
	private ChallengeDao challengeDao;

	@Override
	public final void acceptChallenge(final int challengeId) {
		challengeDao.changeChallengeStatus(challengeId, ChallengeStatus.ACCEPTED);
		// message.show("Opponent accepted.")
		// game.start()
	}

	@Override
	public final void declineChallenge(final int challengeId) {
		// Keep challenge for statistics
		challengeDao.changeChallengeStatus(challengeId, ChallengeStatus.DECLINED);
		// or remove it from DB
		// challengeDao.removeChallengeById(challengeId);
		// message.show("Opponent declined.")
	}

	@Override
	public void createChallenge(@NotNull final int whitePlayerId, @NotNull final int blackPlayerId) {
		int currentSize = challengeDao.getMockingChallengeTableList().size();
		int id = currentSize++;
		Date startDate = new Date();
		Date endDate = addDaysToGivenDate(startDate, FOURTEEN_DAYS);

		ChallengeTo newChallenge = new ChallengeTo(id, whitePlayerId, blackPlayerId, startDate, endDate,
				ChallengeStatus.WAITING_FOR_REPLY);
		challengeDao.addNewChallenge(newChallenge);
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
		return challengeDao.getMockingChallengeTableList();
	}

	@Override
	public void saveChallenge(ChallengeTo challenge) {
		challengeDao.addNewChallenge(challenge);
	}
	
	@Override
	public ChallengeTo findChallengeById(int id) {
		return challengeDao.getChallengeById(id);
	}

	@Override
	public void deleteChallengeById(int id) {
		challengeDao.deleteChallengeById(id);
	}

	@Override
	public List<ChallengeTo> findAllChallengesByUser(int userId) {
		return challengeDao.findAllChallengesByUser(userId);
	}

	@Override
	public void deleteAllChallenges() {
		challengeDao.deleteListOfChallengeTo();
	}
}
