package com.capgemini.chess.service;

import com.capgemini.chess.service.to.PlayerTo;

public interface UserService {
	
	public PlayerTo findUserById(Long id);
	
}
