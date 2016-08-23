package com.capgemini.chess.dao;

import java.util.List;

import com.capgemini.chess.service.to.UserProfileTo;

public interface UserProfileDao {

	/**
	 * Create user profile list mocking Database "USERS" table.
	 */
	void createListOfUserProfileTO();

	/**
	 * Clear user profile list mocking Database "USERS" table.
	 */
	void deleteListOfUserProfileTO();

	/**
	 * Get user profile from list by given ID.
	 * 
	 * @param id
	 *            - ID to identify user profile.
	 * @return UserProfileTO
	 */
	UserProfileTo getUserProfileById(int id);

	/**
	 * Getter for list mocking Database table "USERS".
	 * 
	 * @return List<UserProfileTO>
	 */
	List<UserProfileTo> getMockingUserProfileTableList();

}
