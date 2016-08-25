package com.capgemini.chess.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.dataaccess.enums.ChallengeStatus;
import com.capgemini.chess.generated.entities.ChallengeEntity;
import com.capgemini.chess.service.ChallengeService;
import com.capgemini.chess.service.mapper.ChallengeMapper;
import com.capgemini.chess.service.to.ChallengeTo;
import com.capgemini.chess.service.to.PlayerTo;

@Service
@Transactional
public class ChallengeServiceImpl implements ChallengeService {

	private static int FOURTEEN_DAYS = 14;

	@Autowired
	private ChallengeDao challengeDao;
	
	@Autowired
	protected EntityManager entityManager;
	
	@Override
	public final void acceptChallenge(ChallengeTo challenge) {
		challenge.setStatus(ChallengeStatus.ACCEPTED);
		ChallengeEntity entity = ChallengeMapper.map(challenge);
		challengeDao.update(entity);
	}

	@Override
	public final void declineChallenge(ChallengeTo challenge) {
		challenge.setStatus(ChallengeStatus.DECLINED);
		ChallengeEntity entity = ChallengeMapper.map(challenge);
		challengeDao.update(entity);
	}

	@Override
	public ChallengeTo createChallenge(PlayerTo whitePlayer, PlayerTo blackPlayer) {
		Date startDate = new Date();
		Date endDate = addDaysToGivenDate(startDate, FOURTEEN_DAYS);

		ChallengeTo newChallenge = new ChallengeTo();
		newChallenge.setWhitePlayer(whitePlayer);
		newChallenge.setBlackPlayer(blackPlayer);
		newChallenge.setStartDate(startDate);
		newChallenge.setEndDate(endDate);
		newChallenge.setStatus(ChallengeStatus.AWAITING_REPLY);
		
		ChallengeEntity newChallengeEntity = ChallengeMapper.map(newChallenge);
		challengeDao.save(newChallengeEntity);
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
		List<ChallengeTo> toResultList = ChallengeMapper.map2TOs(challengeDao.findAll());
		return toResultList;
	}

	@Override
	public ChallengeTo findChallengeById(Long id) {
		ChallengeTo resultTo = ChallengeMapper.map(challengeDao.findOne(id));
		return resultTo;
	}

	@Override
	public void deleteChallengeById(Long id) {
		challengeDao.delete(id);
	}

	@Override
	public List<ChallengeTo> findAllChallengesByUser(PlayerTo user) {
		 List<ChallengeTo> toResultList = ChallengeMapper.map2TOs(challengeDao.findAllChallengesByUser(user));
		return toResultList;
	}

	@Override
	public void deleteAllChallenges() {
		challengeDao.deleteAll();
	}

	@Override
	public ChallengeEntity saveChallenge(ChallengeTo challenge) {
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
		ChallengeEntity challengeEntity = ChallengeMapper.map(challenge);
		challengeDao.update(challengeEntity);
		return challengeEntity;
	}
}
