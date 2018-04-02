package com.revature.hydra.placement;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.revature.beans.Placement;
import com.revature.hydra.placement.application.PlacementRepositoryServiceApplication;
import com.revature.hydra.placement.data.PlacementRepository;

/**
 * Integrating test from controller layer to repository layer
 * Use Spring MVC Test framework to stimulate end-point hitting
 * 
 * @author JIAQI ZHANG
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlacementRepositoryServiceApplication.class)
public class PlacementControllerTest {

	private static final Logger log = Logger.getLogger(PlacementControllerTest.class);

	private Placement testPlacement;

	private MockMvc mockMvc;

	private final String mediaTypeJson = MediaType.APPLICATION_JSON_UTF8_VALUE;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private PlacementRepository placementRepository;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	
	/**
	 * Setup test environment for each test case.
	 */
	@Before
	public void init() {
		log.info("Initializing a test placement object for testing.");
		testPlacement = new Placement(1000, new Timestamp(100_000), new Timestamp(200_000), 577, 577, 120);
		testPlacement = placementRepository.save(testPlacement);
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * Remove possible changes made by test
	 */
	@After
	public void tearDown() {
		log.info("Tear down");
		if (placementRepository.findOneByPlacementId(testPlacement.getPlacementId()) != null) {
			placementRepository.delete(testPlacement);
		}
	}

	
	/**
	 * Test getting a placement for a specific placementId by hitting end-point /one/placement/{id}
	 * 
	 * @throws Exception
	 */
	@Test
	public void findOnePlacementTest() throws Exception {
		log.info("Testing /one/placement/{id} endpoint...");
		mockMvc.perform(get("/one/placement/" + testPlacement.getPlacementId())).andExpect(status().isOk())
				.andExpect(jsonPath("$.placementId", is(testPlacement.getPlacementId())))
				.andExpect(jsonPath("$.clientId", is(testPlacement.getClientId())))
				.andExpect(jsonPath("$.endClientId", is(testPlacement.getEndClientId())))
				.andExpect(jsonPath("$.associateId", is(testPlacement.getAssociateId())));
	}

	/**
	 * Test getting all placements by hitting end-point /all/placement
	 * 
	 * @throws Exception
	 */
	@Test
	public void findAllTest() throws Exception {
		log.info("Testing /all/placement endpoint...");
		this.mockMvc.perform(get("/all/placement")).andExpect(status().isOk())
				.andExpect(content().contentType(mediaTypeJson));
	}
	
	/**
	 * Test getting placements of an associate by hitting end-point /all/placement/getByAssociateId/{associateId}
	 * 
	 * @throws Exception
	 */
	@Test
	public void getPlacementsByAssociateIdTest() throws Exception {
		log.info("Testing /all/placement/getByAssociateId/{associateId} endpoint...");
		mockMvc.perform(get("/all/placement/getByAssociateId/" + testPlacement.getAssociateId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[*].associateId", everyItem(is(testPlacement.getAssociateId()))));
	}
	
	/**
	 * Test getting placements of a client by hitting end-point /all/placement/getByClientId/{clientId}
	 * 
	 * @throws Exception
	 */
	@Test
	public void getPlacementsByClientIdTest() throws Exception {
		log.info("Testing /all/placement/getByClientId/{clientId} endpoint...");
		mockMvc.perform(get("/all/placement/getByClientId/" + testPlacement.getClientId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[*].clientId", everyItem(is(testPlacement.getClientId()))));
	}

	/**
	 * Test creating a placement by hitting end-point /placement/create
	 * 
	 * @throws Exception
	 */
	@Test
	public void createPlacementTest() throws Exception {
		log.info("Testing /placement/create endpoint...");
		String placementJson = json(testPlacement);

		this.mockMvc.perform(post("/placement/create").content(placementJson).contentType(this.mediaTypeJson))
				.andExpect(status().isCreated());
	}

	/**
	 * Test updating an existing placement by hitting end-point /placement/update
	 * 
	 * @throws Exception
	 */
	@Test
	public void updatePlacementTest() throws Exception {
		log.info("Testing /placement/update endpoint...");
		this.testPlacement.setEndClientId(5555);
		this.mockMvc.perform(put("/placement/update").content(this.json(testPlacement)).contentType(this.mediaTypeJson))
				.andExpect(status().isNoContent());
	}

	/**
	 * Test deleting a placement by hitting end-point /placement/delete/{id}
	 * 
	 * @throws Exception
	 */
	@Test
	public void deletePlacementTest() throws Exception {
		log.info("Testing /placement/delete/{id} endpoint...");
		this.mockMvc.perform(delete("/placement/delete/" + testPlacement.getPlacementId()))
				.andExpect(status().isNoContent());
	}

	protected String json(Object obj) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(obj, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
