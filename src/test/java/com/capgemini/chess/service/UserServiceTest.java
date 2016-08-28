package com.capgemini.chess.service;

import static org.junit.Assert.assertNotNull;

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
	public void testReadUserSuccessful() throws Exception {

		// when
		PlayerTo userTO = userService.findUserById(1L);
		assertNotNull(userTO);
	}

}
