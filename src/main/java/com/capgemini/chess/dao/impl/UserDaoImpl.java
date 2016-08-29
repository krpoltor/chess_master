package com.capgemini.chess.dao.impl;

import org.springframework.stereotype.Component;

import com.capgemini.chess.dao.UserDao;
import com.capgemini.chess.generated.entities.PlayerEntity;

@Component
public class UserDaoImpl extends AbstractDao<PlayerEntity, Long> implements UserDao {

}
