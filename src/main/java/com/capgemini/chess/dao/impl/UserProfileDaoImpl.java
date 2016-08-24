package com.capgemini.chess.dao.impl;

import org.springframework.stereotype.Component;

import com.capgemini.chess.dao.UserProfileDao;
import com.capgemini.chess.generated.entities.UserEntity;

@Component
public class UserProfileDaoImpl extends AbstractDao<UserEntity, Long> implements UserProfileDao {

}
