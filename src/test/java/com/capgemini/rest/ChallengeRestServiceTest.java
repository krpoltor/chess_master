package com.capgemini.rest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "ChallengeRestServiceTest-context.xml")
@WebAppConfiguration
public class ChallengeRestServiceTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
