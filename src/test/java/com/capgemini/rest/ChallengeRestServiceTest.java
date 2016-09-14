package com.capgemini.rest;

import static java.lang.Math.toIntExact;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
import com.capgemini.chess.service.ChallengeService;
import com.capgemini.chess.service.to.ChallengeTo;
import com.capgemini.utils.FileUtils;

/**
 * Test class for testing {@link ChallengeRestService}<br>
 * Checklist:<br>
 * 1. Test getting every challenge from database.<br>
 * 2. Test getting challenge by its ID.<br>
 * 3. Test updating challenge.<br>
 * 4. Test adding new challenge to database.<br>
 * 5. Test getting challenges by user.<br>
 * 6. Test deleting challenges by ID.<br>
 * 7. Test deleting every challenge from database.<br>
 * 
 * @author KRPOLTOR
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/test/resources/com/capgemini/chess/rest/ChallengeRestServiceTest-context.xml" })
@WebAppConfiguration
public class ChallengeRestServiceTest {

	@Autowired
	private ChallengeService challengeService;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		Mockito.reset(challengeService);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	private ChallengeTo stubChallengeTo() {
		ChallengeTo challengeTo = new ChallengeTo();
		challengeTo.setId(1L);
		challengeTo.setWhitePlayer(null);
		challengeTo.setBlackPlayer(null);
		Date testDate = new Date();
		challengeTo.setStartDate(testDate);
		challengeTo.setEndDate(testDate);
		challengeTo.setStatus(ChallengeStatus.INIT);
		return challengeTo;
	}

	/**
	 * Test for getting all challenges.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldGetAllChallenges() throws Exception {
		// given:
		ChallengeTo challengeTo = stubChallengeTo();
		// when
		Mockito.when(challengeService.findAllChallenges()).thenReturn(Arrays.asList(challengeTo));

		ResultActions response = this.mockMvc//
				.perform(get("/services/challenges")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content("1"));

		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("[0].id").value(toIntExact(challengeTo.getId())))
				.andExpect(jsonPath("[0].whitePlayer").value(challengeTo.getWhitePlayer()))
				.andExpect(jsonPath("[0].blackPlayer").value(challengeTo.getBlackPlayer()))
				.andExpect(jsonPath("[0].startDate").value(challengeTo.getStartDate().getTime()))
				.andExpect(jsonPath("[0].endDate").value(challengeTo.getEndDate().getTime()))
				.andExpect(jsonPath("[0].status").value(challengeTo.getStatus().toString()));
	}

	/**
	 * Test for getting all challenges by user.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldGetAllUserChallenges() throws Exception {
		// given:
		ChallengeTo challengeTo = stubChallengeTo();

		Mockito.when(challengeService.findAllChallengesByUserId(Mockito.anyLong()))//
				.thenReturn(Arrays.asList(challengeTo));
		// when
		ResultActions response = this.mockMvc//
				.perform(get("/services/challenges/byUserId/1")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content("1"));
		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("[0].id").value(toIntExact(challengeTo.getId())))
				.andExpect(jsonPath("[0].whitePlayer").value(challengeTo.getWhitePlayer()))
				.andExpect(jsonPath("[0].blackPlayer").value(challengeTo.getBlackPlayer()))
				.andExpect(jsonPath("[0].startDate").value(challengeTo.getStartDate().getTime()))
				.andExpect(jsonPath("[0].endDate").value(challengeTo.getEndDate().getTime()))
				.andExpect(jsonPath("[0].status").value(challengeTo.getStatus().toString()));
	}

	/**
	 * Test for getting challenge by ID.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldGetChallengeById() throws Exception {
		// given:
		ChallengeTo challengeTo = stubChallengeTo();

		Mockito.when(challengeService.findChallengeById(Mockito.anyLong())).thenReturn(challengeTo);
		// when
		ResultActions response = this.mockMvc//
				.perform(get("/services/challenges/2")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content("1"));
		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("$.id").value(toIntExact(challengeTo.getId())))
				.andExpect(jsonPath("$.whitePlayer").value(challengeTo.getWhitePlayer()))
				.andExpect(jsonPath("$.blackPlayer").value(challengeTo.getBlackPlayer()))
				.andExpect(jsonPath("$.startDate").value(challengeTo.getStartDate().getTime()))
				.andExpect(jsonPath("$.endDate").value(challengeTo.getEndDate().getTime()))
				.andExpect(jsonPath("$.status").value(challengeTo.getStatus().toString()));
	}

	/**
	 * Test for adding new challenge to database.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldSaveChallenge() throws Exception {
		// given
		File file = FileUtils.getFileFromClasspath("file:src/test/resources/json/challengeToSave.json");
		String json = FileUtils.readFileToString(file);
		// when
		ResultActions response = this.mockMvc//
				.perform(post("/services/challenges")//
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
	public void shouldUpdateChallenge() throws Exception {
		// given
		File file = FileUtils.getFileFromClasspath("file:src/test/resources/json/challengeToSave.json");
		String json = FileUtils.readFileToString(file);

		ChallengeTo challengeTo = stubChallengeTo();

		Mockito.when(challengeService.findChallengeById(Mockito.anyLong())).thenReturn(challengeTo);

		Mockito.when(challengeService.updateChallenge(Mockito.any(ChallengeTo.class))).thenReturn(challengeTo);
		// when
		ResultActions response = this.mockMvc//
				.perform(put("/services/challenges/1")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content(json.getBytes()));
		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("$.id").value(toIntExact(challengeTo.getId())));
	}

	/**
	 * Test for deleting challenge by ID.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldDeleteChallengeByID() throws Exception {
		// given
		ChallengeTo challengeTo = stubChallengeTo();
		Mockito.when(challengeService.findChallengeById(Mockito.anyLong())).thenReturn(challengeTo);

		Mockito.doNothing().when(challengeService).deleteChallengeById(Mockito.anyLong());
		// when
		ResultActions response = this.mockMvc//
				.perform(delete("/services/challenges/1")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON));
		// then
		response.andExpect(status().isOk());

		Mockito.verify(challengeService, Mockito.times(1)).deleteChallengeById(Mockito.anyLong());
	}

	/**
	 * Test for deleting every challenge in database.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldDeleteEveryChallenge() throws Exception {
		// given
		Mockito.doNothing().when(challengeService).deleteAllChallenges();
		// when
		ResultActions response = this.mockMvc//
				.perform(delete("/services/challenges")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON));
		// then
		response.andExpect(status().isOk());

		Mockito.verify(challengeService, Mockito.times(1)).deleteAllChallenges();
	}

	@Test
	public void shouldNotFindChallenge() throws Exception {
		// given
		// when
		ResultActions response = this.mockMvc//
				.perform(get("/services/challenges/50")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content("1"));
		// then
		response.andExpect(status().isNotFound());
	}

	@Test
	public void shouldExpectEmptyDatabase() throws Exception {
		// given
		List<ChallengeTo> emptyList = new LinkedList<ChallengeTo>();

		Mockito.when(challengeService.findAllChallenges()).thenReturn(emptyList);
		// when
		ResultActions response = this.mockMvc//
				.perform(get("/services/challenges/")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content("1"));
		// then
		response.andExpect(status().isNotFound());
	}

	@Test
	public void shouldExpectEmptyUserChallengesList() throws Exception {
		// given
		List<ChallengeTo> emptyList = new LinkedList<ChallengeTo>();

		Mockito.when(challengeService.findAllChallengesByUserId(Mockito.anyLong())).thenReturn(emptyList);
		// when
		ResultActions response = this.mockMvc//
				.perform(get("/services/challenges/byUser/1")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content("1"));
		// then
		response.andExpect(status().isNotFound());
	}

	@Test
	public void shouldNotSaveChallenge_CopyExists() throws Exception {
		// given
		File file = FileUtils.getFileFromClasspath("file:src/test/resources/json/challengeToSave.json");
		String json = FileUtils.readFileToString(file);

		Mockito.when(challengeService.doesThisChallengeExist(Mockito.any())).thenReturn(true);
		// when
		ResultActions response = this.mockMvc//
				.perform(post("/services/challenges")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content(json.getBytes()));
		// then
		response.andExpect(status().isConflict());
	}

	@Test
	public void shouldNotSaveChallenge_PlayersIdsEqual() throws Exception {
		// given
		File file = FileUtils.getFileFromClasspath("file:src/test/resources/json/challengeToSave.json");
		String json = FileUtils.readFileToString(file);

		Mockito.when(challengeService.arePlayersIdTheSame(Mockito.any())).thenReturn(true);
		// when
		ResultActions response = this.mockMvc//
				.perform(post("/services/challenges")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content(json.getBytes()));
		// then
		response.andExpect(status().isForbidden());
	}

	@Test
	public void shouldNotDeleteChallenge_ChallengeNotFound() throws Exception {
		// given
		Mockito.when(challengeService.findChallengeById(Mockito.anyLong())).thenReturn(null);
		Mockito.doNothing().when(challengeService).deleteChallengeById(Mockito.anyLong());
		// when
		ResultActions response = this.mockMvc//
				.perform(delete("/services/challenges/1")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON));
		// then
		response.andExpect(status().isNotFound());
	}

	@Test
	public void shouldNotUpdateChallenge_ChallengeNotFound() throws Exception {
		// given
		File file = FileUtils.getFileFromClasspath("file:src/test/resources/json/challengeToSave.json");
		String json = FileUtils.readFileToString(file);

		Mockito.when(challengeService.findChallengeById(Mockito.anyLong())).thenReturn(null);
		// when
		ResultActions response = this.mockMvc//
				.perform(put("/services/challenges/1")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content(json.getBytes()));
		// then
		response.andExpect(status().isNotFound());
	}

	@Test
	public void shouldNotUpdateChallenge_PlayersIdsEqual() throws Exception {
		// given
		ChallengeTo challengeTo = stubChallengeTo();
		File file = FileUtils.getFileFromClasspath("file:src/test/resources/json/challengeToSave.json");
		String json = FileUtils.readFileToString(file);

		Mockito.when(challengeService.findChallengeById(Mockito.anyLong())).thenReturn(challengeTo);

		Mockito.when(challengeService.arePlayersIdTheSame(Mockito.any())).thenReturn(true);
		// when
		ResultActions response = this.mockMvc//
				.perform(put("/services/challenges/1")//
						.accept(MediaType.APPLICATION_JSON)//
						.contentType(MediaType.APPLICATION_JSON)//
						.content(json.getBytes()));
		// then
		response.andExpect(status().isConflict());
	}
}