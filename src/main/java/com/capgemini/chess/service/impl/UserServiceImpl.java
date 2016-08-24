package com.capgemini.chess.service.impl;

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

	@Override
	public PlayerTo findUserById(Long id) {
		PlayerTo foundUser = PlayerMapper.map(userProfileDao.findOne(id));
		return foundUser;
	}
}
