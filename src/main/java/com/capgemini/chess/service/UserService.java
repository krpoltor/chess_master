package com.capgemini.chess.service;

import com.capgemini.chess.service.to.UserProfileTo;

public interface UserService {
	
	public UserProfileTo readUser(Long id);
	
}
