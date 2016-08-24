package com.capgemini.chess.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.generated.entities.ChallengeEntity;
import com.capgemini.chess.service.to.UserProfileTo;

@Component
public class ChallengeDaoImpl extends AbstractDao<ChallengeEntity, Long> implements ChallengeDao {

	@Autowired
	protected EntityManager entityManager;

	@Override
	public void deleteOverdueChallengesFromListOfChallengeTo() {
		Query query = //
				entityManager.createQuery("DELETE FROM ChallengeEntity challenge"
						+ "	WHERE "
							+ "challenge.status = 'OVERDUE'");
		
		int deletedCount = query.executeUpdate();
	}

	@Override
	public List<ChallengeEntity> findAllChallengesByUser(UserProfileTo user) {
		TypedQuery<ChallengeEntity> query = //
				entityManager.createQuery("SELECT challenge FROM ChallengeEntity challenge"//
						+ " WHERE " 
							+ "(challenge.sender.id) = (:userId)"//
						+ " OR " 
							+ "(challenge.receiver.id) = (:userId)"//
						+ ")", ChallengeEntity.class);//
		query.setParameter("userId", user.getId());
		return query.getResultList();
	}

	@Override
	public List<ChallengeEntity> findAllChallengesByUserId(Long userId) {
		TypedQuery<ChallengeEntity> query = //
				entityManager.createQuery("SELECT challenge FROM ChallengeEntity challenge"//
						+ " WHERE " 
							+ "(challenge.sender.id) = (:userId)"//
						+ " OR " 
							+ "(challenge.receiver.id) = (:userId)"//
						+ ")", ChallengeEntity.class);//
		query.setParameter("userId", userId);
		return query.getResultList();
	}

}
