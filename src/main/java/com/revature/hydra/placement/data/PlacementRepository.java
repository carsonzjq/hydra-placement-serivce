package com.revature.hydra.placement.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Placement;

/**
 * PlacementRepository Data Access Object with various methods to communicate with database
 *
 */
@Repository
public interface PlacementRepository extends JpaRepository<Placement, Integer> {

	/**
	 * Find a certain placement with specific placementId
	 * 
	 * @param placementId
	 * @return placement with given placementId
	 */
	Placement findOneByPlacementId(Integer placementId);

	/**
	 * Find all Placements.
	 * 
	 * @return List of Placements
	 */
	List<Placement> findAll();

	/**
	 * Find list of placements with specific associateId
	 * 
	 * @param associateId
	 * @return List of placements with given associateId
	 */
	List<Placement> findAllByAssociateId(Integer associateId);

	/**
	 * Find list of placements with specific clientId
	 * 
	 * @param clientId
	 * @return List of placements with given clientId
	 */
	List<Placement> findAllByClientId(Integer clientId);

	/**
	 * Find list of placements with specific endClientId
	 * 
	 * @param endClientId
	 * @return List of placements with given endClientId
	 */
	List<Placement> findAllByEndClientId(Integer endClientId);

}
