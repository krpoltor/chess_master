package com.capgemini.chess.service.impl;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.chess.dao.UserProfileDao;
import com.capgemini.chess.service.UserService;
import com.capgemini.chess.service.mapper.PlayerMapper;
import com.capgemini.chess.service.to.PlayerTo;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserProfileDao userProfileDao;

	@Autowired
	protected EntityManager entityManager;

	private static Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

	@Override
	public PlayerTo findUserById(Long id) {

		LOGGER.info("Finding user with id: " + id);
		PlayerTo foundUser = null;

		try {
			foundUser = PlayerMapper.map(userProfileDao.findOne(id));
		} catch (PersistenceException e) {
			LOGGER.severe("Error in finding user!");
			e.printStackTrace();
		}

		return foundUser;
	}
}
