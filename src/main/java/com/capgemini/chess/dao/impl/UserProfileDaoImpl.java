package com.capgemini.chess.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.capgemini.chess.dao.UserProfileDao;
import com.capgemini.chess.service.to.UserProfileTo;

@Component
public class UserProfileDaoImpl implements UserProfileDao {

	/**
	 * List mocking Database table "USERS".
	 */
	private static List<UserProfileTo> mockingUserProfileTableList = new LinkedList<UserProfileTo>();

	/**
	 * Constructor simulating creation of Database "USERS" table.
	 */
	public UserProfileDaoImpl() {
		createListOfUserProfileTO();
	}

	@Override
	public final void createListOfUserProfileTO() {
		List<UserProfileTo> temporaryList = new LinkedList<UserProfileTo>();
		temporaryList.add(new UserProfileTo(0, "one", "one", "john", "", "", "", ""));
		temporaryList.add(new UserProfileTo(1, "two", "two", "john", "", "", "", ""));
		temporaryList.add(new UserProfileTo(2, "three", "three", "john", "", "", "", ""));
		temporaryList.add(new UserProfileTo(3, "four", "four", "john", "", "", "", ""));
		setMockingUserProfileTableList(temporaryList);
	}

	@Override
	public final void deleteListOfUserProfileTO() {
		mockingUserProfileTableList.clear();
	}

	public List<UserProfileTo> getMockingUserProfileTableList() {
		return mockingUserProfileTableList;
	}

	/**
	 * Setter for list mocking Database table "USERS".
	 * 
	 * @param listAsMockingUserProfileTableList
	 *            - List<UserProfileTO>
	 */
	public static void setMockingUserProfileTableList(final List<UserProfileTo> listAsMockingUserProfileTableList) {
		UserProfileDaoImpl.mockingUserProfileTableList = listAsMockingUserProfileTableList;
	}

	@Override
	public final UserProfileTo getUserProfileById(final int id) {
		return mockingUserProfileTableList.get(id);
	}
}
