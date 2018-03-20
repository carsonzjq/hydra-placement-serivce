package com.revature.hydra.placement.controller;

import java.util.List;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Placement;
import com.revature.hydra.placement.service.PlacementService;

@RestController
@CrossOrigin
@EnableAutoConfiguration
public class PlacementController {

	private static final Logger log = Logger.getLogger(PlacementController.class);

	private PlacementService placementService;

	@Autowired
	public void setPlacementService(PlacementService placementService) {
		this.placementService = placementService;
	}
	
	

	/**
	 * Get placement By placmentId
	 * 
	 * @param id
	 * @return Response Entity with corresponding placement
	 */
	@RequestMapping(value = "/one/placement/{id}", method = RequestMethod.GET)
	public ResponseEntity<Placement> findOnePlacement(@PathVariable Integer id) {
		log.info("Finding placement with placementId: " + id);
		Placement placement = placementService.findOneById(id);
		return new ResponseEntity<>(placement, HttpStatus.OK);
	}

	/**
	 * Get all placements
	 * 
	 * @return ResponseEntity with all placements
	 */
	@RequestMapping(value = "/all/placement", method = RequestMethod.GET)
	public ResponseEntity<List<Placement>> findAllPlacement() {
		log.info("Finding all placements...");
		List<Placement> placements = placementService.findAll();
		return new ResponseEntity<>(placements, HttpStatus.OK);
	}

	/**
	 * Get all placements for certain associate
	 * 
	 * @param associateId
	 * @return ResponseEntity that has the list of placement for specific associate
	 */
	@RequestMapping(value = "/all/placement/getByAssociateId/{associateId}", method = RequestMethod.GET)
	public ResponseEntity<List<Placement>> getPlacementsByAssociateId(@PathVariable Integer associateId) {
		log.info("Finding placement(s) with associateId: " + associateId);
		List<Placement> placements = placementService.findAllByAssociateId(associateId);
		return new ResponseEntity<List<Placement>>(placements, HttpStatus.OK);
	}

	/**
	 * Create placement
	 *
	 * @param placement to save
	 * @return the response entity with saved placement
	 */
	@RequestMapping(value = "/all/placement/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Placement> createPlacement(@Valid @RequestBody Placement placement) {
		log.info("Saving placement: " + placement);
		return new ResponseEntity<>(placementService.save(placement), HttpStatus.CREATED);
	}

	/**
	 * Update placement
	 *
	 * @param placement to update
	 * @return the response entity
	 */
	@RequestMapping(value = "/all/placement/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updatePlacement(@Valid @RequestBody Placement placement) {
		log.info("Updating placement: " + placement);
		placementService.update(placement);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete placement
	 *
	 * @param id 
	 * @return the response entity
	 */
	@RequestMapping(value = "/all/placement/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePlacement(@PathVariable int id) {
		Placement placement = new Placement();
		placement.setPlacementId(id);
		log.info("Deleting placement: " + id);
		placementService.delete(placement);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
