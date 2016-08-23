package com.capgemini.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.capgemini.chess.dataaccess.enums.ChallengeStatus;
import com.capgemini.chess.rest.ChallengeRestService;
import com.capgemini.chess.service.UserChallengeService;
import com.capgemini.chess.service.to.ChallengeTo;
import com.capgemini.utils.FileUtils;

/**
 * Test class for testing {@link ChallengeRestService}<br>
 * Checklist:<br>
 * 1. Test getting every challenge from database.<br>
 * 2. Test getting challenge by its ID.<br>
 * 3.Test updating challenge.<br>
 * 4. Test adding new challenge to database.<br>
 * 5. Test getting challenges by user.<br>
 * 6. Test deleting challenges by ID.<br>
 * 7. Test deleting every challenge from database.<br>
 * 
 * @author KRPOLTOR
 *
 */

// bean creation exception for autowiring userChallengeService

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = "ChallengeRestServiceTest-context.xml")
@ContextConfiguration({ "classpath*:src/test/resources/com/capgemini/chess/rest" })
@WebAppConfiguration
public class ChallengeRestServiceTest {

	@Autowired
	private UserChallengeService userChallengeService;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		Mockito.reset(userChallengeService);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	/**
	 * Test for getting all challenges.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShouldGetAllChallenges() throws Exception {
		// given:
		Date testDate = new Date();
		final ChallengeTo ChallengeTo1 = new ChallengeTo(0, 1, 2, testDate, testDate, ChallengeStatus.ACCEPTED);

		// when
		Mockito.when(userChallengeService.findAllChallenges()).thenReturn(Arrays.asList(ChallengeTo1));
		ResultActions response = this.mockMvc//
				.perform(get("/rest/challenges")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content("1"));

		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("[0].id").value(ChallengeTo1.getId()))
				.andExpect(jsonPath("[0].whitePlayerId").value(ChallengeTo1.getWhitePlayerId()))
				.andExpect(jsonPath("[0].blackPlayerId").value(ChallengeTo1.getBlackPlayerId()))
				.andExpect(jsonPath("[0].startDate").value(ChallengeTo1.getStartDate()))
				.andExpect(jsonPath("[0].endDate").value(ChallengeTo1.getEndDate()))
				.andExpect(jsonPath("[0].status").value(ChallengeTo1.getStatus()));
	}

	/**
	 * Test for getting all challenges by user.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShouldGetAllUserChallenges() throws Exception {
		// given:
		Date testDate = new Date();
		final ChallengeTo ChallengeTo1 = new ChallengeTo(0, 1, 2, testDate, testDate, ChallengeStatus.ACCEPTED);

		Mockito.when(userChallengeService.findAllChallengesByUser(Mockito.anyInt()))
				.thenReturn(Arrays.asList(ChallengeTo1));
		// when
		ResultActions response = this.mockMvc//
				.perform(get("/rest/challenges/1")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content("1"));
		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("[0].id").value(ChallengeTo1.getId()))
				.andExpect(jsonPath("[0].whitePlayerId").value(ChallengeTo1.getWhitePlayerId()))
				.andExpect(jsonPath("[0].blackPlayerId").value(ChallengeTo1.getBlackPlayerId()))
				.andExpect(jsonPath("[0].startDate").value(ChallengeTo1.getStartDate()))
				.andExpect(jsonPath("[0].endDate").value(ChallengeTo1.getEndDate()))
				.andExpect(jsonPath("[0].status").value(ChallengeTo1.getStatus()));
	}

	/**
	 * Test for getting challenge by ID.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShouldGetChallengeById() throws Exception {
		// given:
		Date testDate = new Date();
		final ChallengeTo challengeTo = new ChallengeTo(0, 1, 2, testDate, testDate, ChallengeStatus.ACCEPTED);
		Mockito.when(userChallengeService.findChallengeById(Mockito.anyInt())).thenReturn(challengeTo);
		// when
		ResultActions response = this.mockMvc//
				.perform(get("/rest/challenges/2")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content("1"));
		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("[0].id").value(challengeTo.getId()))
				.andExpect(jsonPath("[0].whitePlayerId").value(challengeTo.getWhitePlayerId()))
				.andExpect(jsonPath("[0].blackPlayerId").value(challengeTo.getBlackPlayerId()))
				.andExpect(jsonPath("[0].startDate").value(challengeTo.getStartDate()))
				.andExpect(jsonPath("[0].endDate").value(challengeTo.getEndDate()))
				.andExpect(jsonPath("[0].status").value(challengeTo.getStatus()));
	}

	/**
	 * Test for adding new challenge to database.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShouldSaveChallenge() throws Exception {
		// given
		File file = FileUtils.getFileFromClasspath("classpath:src/test/resorces/json/challengeToSave.json");
		String json = FileUtils.readFileToString(file);
		// when
		ResultActions response = this.mockMvc//
				.perform(post("/rest/challenges")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content(json.getBytes()));
		// then
		response.andExpect(status().isCreated());
	}

	/**
	 * Test for updating challenge in database.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShouldUpdateBook() throws Exception {
		// given
		Date testDate = new Date();
		File file = FileUtils.getFileFromClasspath("classpath:src/test/resorces/json/challengeToSave.json");
		String json = FileUtils.readFileToString(file);
		ChallengeTo challengeTo = new ChallengeTo(0, 1, 2, testDate, testDate, ChallengeStatus.ACCEPTED);
		Mockito.when(userChallengeService.findChallengeById(Mockito.anyInt())).thenReturn(challengeTo);
		// when
		ResultActions response = this.mockMvc//
				.perform(put("/rest/challenges/1")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content(json.getBytes()));
		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("[0].id").value(challengeTo.getId()))
				.andExpect(jsonPath("[0].whitePlayerId").value(challengeTo.getWhitePlayerId()))
				.andExpect(jsonPath("[0].blackPlayerId").value(challengeTo.getBlackPlayerId()))
				.andExpect(jsonPath("[0].startDate").value(challengeTo.getStartDate()))
				.andExpect(jsonPath("[0].endDate").value(challengeTo.getEndDate()))
				.andExpect(jsonPath("[0].status").value(challengeTo.getStatus()));
	}

	/**
	 * Test for deleting challenge by ID.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShouldDeleteChallengeByID() throws Exception {
		Date testDate = new Date();
		ChallengeTo testBook = new ChallengeTo(0, 1, 2, testDate, testDate, ChallengeStatus.ACCEPTED);
		// given
		Mockito.when(userChallengeService.findChallengeById(Mockito.anyInt())).thenReturn(testBook);
		Mockito.doNothing().when(userChallengeService).deleteChallengeById(Mockito.anyInt());
		// when
		ResultActions response = this.mockMvc//
				.perform(delete("/rest/challenges/1")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON));
		// then
		response.andExpect(status().isNoContent());
		Mockito.verify(userChallengeService, Mockito.times(1)).deleteChallengeById(Mockito.anyInt());
	}

	/**
	 * Test for deleting every challenge in database.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShouldDeleteEveryChallenge() throws Exception {
		// given
		Mockito.doNothing().when(userChallengeService).deleteAllChallenges();
		// when
		ResultActions response = this.mockMvc//
				.perform(delete("/rest/challenges")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON));
		// then
		response.andExpect(status().isNoContent());
		Mockito.verify(userChallengeService, Mockito.times(1)).deleteAllChallenges();
	}
}