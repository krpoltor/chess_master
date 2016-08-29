package com.capgemini.chess.dao.impl;

import org.springframework.stereotype.Component;

import com.capgemini.chess.dao.PlayerDao;
import com.capgemini.chess.generated.entities.PlayerEntity;

@Component
public class PlayerDaoImpl extends AbstractDao<PlayerEntity, Long> implements PlayerDao {

}
