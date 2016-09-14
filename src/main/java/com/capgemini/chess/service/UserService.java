package com.capgemini.chess.service;

import com.capgemini.chess.service.to.PlayerTo;

public interface UserService {
	
	/**
	 * Find user by id.
	 * @param id of a user.
	 * @return PlayerTo
	 */
	public PlayerTo findUserById(Long id);
	
	/**
	 * Find user by login.
	 * @param id of a user.
	 * @return PlayerTo
	 */
	public PlayerTo findUserByLogin(String login);
	
}
