package com.revature.hydra.placement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.beans.Placement;
import com.revature.hydra.placement.application.PlacementRepositoryServiceApplication;
import com.revature.hydra.placement.data.PlacementRepository;

/**
 * Unit tests on methods of PlacementRepository class
 * 
 * @author JIAQI ZHANG
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlacementRepositoryServiceApplication.class)
public class PlacementRepositoryTest {

	private static final Logger log = Logger.getLogger(PlacementRepositoryTest.class);

	@Autowired
	PlacementRepository placementRepository;

	Placement testPlacement;

	@Before
	public void init() {
		log.info("Initializing a test placement object for testing.");
		testPlacement = new Placement(1000, new Timestamp(100_000), new Timestamp(200_000), 5501, 5501, 5505);
		testPlacement = placementRepository.save(testPlacement);
	}

	@After
	public void teardown() {
		log.info("Tear down");
		if (placementRepository.findOneByPlacementId(testPlacement.getPlacementId()) != null) {
			placementRepository.delete(testPlacement);
		}
	}

	@Test
	public void addPlacement() {
		log.info("Test adding a placement.");
		testPlacement = new Placement(1001, new Timestamp(100_000), new Timestamp(200_000), 5510, 5510, 5503);
		Placement savedPlacement = placementRepository.save(testPlacement);

		assertTrue(placementRepository.findAll().contains(savedPlacement));
	}

	@Test
	public void findOneByPlacmentId() {
		log.info("Test getting a placement by placementId.");
		Placement placement = placementRepository.findOneByPlacementId(testPlacement.getPlacementId());

		assertEquals(testPlacement, placement);
	}

	@Test
	public void findAll() {
		log.info("Test getting all placements.");
		List<Placement> placements = placementRepository.findAll();

		assertFalse(placements.isEmpty());
	}

	@Test
	public void findAllByAssociateId() {
		log.info("Test getting placements by AssociateId");
		List<Placement> placements = placementRepository.findAllByAssociateId(testPlacement.getAssociateId());

		assertNotNull(placements);
		assertTrue(placements.contains(testPlacement));
	}

	@Test
	public void findAllByClientId() {
		log.info("Test getting placements by ClientId");
		List<Placement> placements = placementRepository.findAllByClientId(testPlacement.getClientId());

		assertNotNull(placements);
		assertTrue(placements.contains(testPlacement));
	}

	@Test
	public void findAllByEndClientId() {
		log.info("Test getting placements by EndClientId");
		List<Placement> placements = placementRepository.findAllByEndClientId(testPlacement.getEndClientId());

		assertNotNull(placements);
		assertTrue(placements.contains(testPlacement));
	}

	@Test
	public void updatePlacement() {
		log.info("Test updating a placement.");
		testPlacement.setAssociateId(5507);
		Placement updatedPlacement = placementRepository.save(testPlacement);

		assertEquals(updatedPlacement.getAssociateId(), testPlacement.getAssociateId());
	}

	@Test
	public void deletePlacement() {
		log.info("Test deleting a placement.");
		placementRepository.delete(testPlacement);

		assertNull(placementRepository.findOneByPlacementId(testPlacement.getPlacementId()));
	}

}
