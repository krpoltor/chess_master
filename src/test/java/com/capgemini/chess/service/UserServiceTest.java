package com.capgemini.chess.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.ChessApplication;
import com.capgemini.chess.service.to.PlayerTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChessApplication.class)
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void shouldGetUser() throws Exception {
		// given
		Long expectedId = 1L;
		String expectedName = "name1";
		String expectedSurname = "surname1";
		String expectedLogin = "player1";
		String expectedPassword = "pass1";
		String expectedAboutMe = "aboutMe1";
		String expectedLifeMotto = "lifeMotto1";
		// when
		PlayerTo userTO = userService.findUserById(1L);
		
		//then
		assertEquals(expectedId, userTO.getId());
		assertEquals(expectedName, userTO.getName());
		assertEquals(expectedSurname, userTO.getSurname());
		assertEquals(expectedLogin, userTO.getLogin());
		assertEquals(expectedPassword, userTO.getPassword());
		assertEquals(expectedAboutMe, userTO.getAboutMe());
		assertEquals(expectedLifeMotto, userTO.getLifeMotto());
	}

}
