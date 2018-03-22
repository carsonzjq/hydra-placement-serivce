# hydra-placement-service
Fields in the Placement beans are as follows:
* Placement
	* Integer placementId
	* Timestamp startDate
	* Timestamp endDate
	* Integer clientId
	* Integer endClientId
	* Integer associateId

The controller can perform the following functions:
* PlacementController
	* findOnePlacement(@PathVariable Integer id), via a GET to /one/placement/{id}
	* findAllPlacement(), via a GET to /all/placement
	* getPlacementsByAssociateId(@PathVariable Integer associateId), via a GET to /all/placement/getByAssociateId/{associateId}
	* createPlacement(@Valid @RequestBody Placement placement), via a POST to /placement/create
	* updatePlacement(@Valid @RequestBody Placement placement), via a PUT to /placement/update
	* deletePlacement(@PathVariable int id), via a DELETE to /placement/delete/{id}
