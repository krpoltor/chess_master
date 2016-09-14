package com.capgemini.chess.dao;

import java.util.List;

import com.capgemini.chess.generated.entities.PlayerEntity;

public interface UserDao extends Dao<PlayerEntity, Long>{

	/**
	 * Finds player by login. 
	 * 
	 * @param login - player's login.
	 * @return PlayerEntity
	 */
	List<PlayerEntity> findByLogin(String login);
	
}
