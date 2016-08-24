package com.capgemini.chess.dao.impl;

import org.springframework.stereotype.Component;

import com.capgemini.chess.dao.UserProfileDao;
import com.capgemini.chess.generated.entities.PlayerEntity;

@Component
public class UserProfileDaoImpl extends AbstractDao<PlayerEntity, Long> implements UserProfileDao {

}
