package com.revature.hydra.placement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Placement;
import com.revature.hydra.placement.data.PlacementRepository;

/**
 * PlacementService
 * 
 * Placement services implementation
 */
@Service
public class PlacementService {

	@Autowired
	PlacementRepository placementRepository;

	/**
	 * Save a placement
	 * 
	 * @param placement
	 */
	public Placement save(Placement placement) {
		return placementRepository.save(placement);
	}

	/**
	 * Update a placement
	 * 
	 * @param placement
	 */
	public void update(Placement placement) {
		save(placement);
	}

	/**
	 * delete a placement
	 * 
	 * @param placement
	 */
	public void delete(Placement placement) {
		placementRepository.delete(placement);
	}

	/**
	 * Obtain list of all placements from placementRepository
	 * 
	 * @return List<Placement> - List of All Placements
	 */
	public List<Placement> findAll() {
		return placementRepository.findAll();
	}

	/**
	 * Obtain a placement from placementRepository with given placementId
	 * 
	 * @param placementId
	 * @return Placement - The placement with given placementId
	 */
	public Placement findOneById(Integer placementId) {
		return placementRepository.findOneByPlacementId(placementId);
	}

	/**
	 * Obtain list of placements with specific associateId from placementRepository
	 * 
	 * @param associateId
	 * @return List<Placement> - list of placements with given associateId
	 */
	public List<Placement> findAllByAssociateId(Integer associateId) {
		return placementRepository.findAllByAssociateId(associateId);
	}

	/**
	 * Obtain list of placements with specific clientId from placementRepository
	 * 
	 * @param clientId
	 * @return List<Placement> - list of placements with given clientId
	 */
	public List<Placement> findAllByClientId(Integer clientId) {
		return placementRepository.findAllByClientId(clientId);
	}

	/**
	 * Obtain list of placements with specific endClientId from placementRepository
	 * 
	 * @param endClientId
	 * @return List<Placement> - list of placements with given endClientId
	 */
	public List<Placement> findAllByEndClientId(Integer endClientId) {
		return placementRepository.findAllByEndClientId(endClientId);
	}

}
