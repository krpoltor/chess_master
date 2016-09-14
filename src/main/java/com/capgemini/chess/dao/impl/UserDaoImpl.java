package com.capgemini.chess.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.capgemini.chess.dao.UserDao;
import com.capgemini.chess.generated.entities.PlayerEntity;

@Component
public class UserDaoImpl extends AbstractDao<PlayerEntity, Long> implements UserDao {

	@Override
	public List<PlayerEntity> findByLogin(String login) {
		TypedQuery<PlayerEntity> query = //
				entityManager.createQuery("SELECT player FROM PlayerEntity player"//
						+ " WHERE " 
							+ "(player.login = :login"//
						+ ")", PlayerEntity.class);//
		query.setParameter("login", login);
		return query.getResultList();
	}

}
