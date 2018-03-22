package com.revature.beans;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Bean for the Placement table
 * 
 * @author JIAQI ZHANG
 *
 */
@Entity
@Table(name = "HYDRA_PLACEMENT")
@Cacheable
public class Placement implements Serializable{

	private static final long serialVersionUID = 2803643371501250761L;

	@Id
	@Column(name = "PLACEMENT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLACEMENT_ID_SEQUENCE")
	@SequenceGenerator(name = "PLACEMENT_ID_SEQUENCE", sequenceName = "PLACEMENT_ID_SEQUENCE")
	private Integer placementId;
	
	@Column (name = "START_DATE")
	private Timestamp startDate;
	
	@Column (name = "END_DATE")
	private Timestamp endDate;
	
	@Column (name = "CLIENT_ID")
	private Integer clientId;
	
	@Column (name = "END_CLIENT_ID")
	private Integer endClientId;
	
	@Column (name = "ASSOCIATE_ID")
	private Integer associateId;

	public Placement() {
		super();
	}

	public Placement(Integer placementId, Timestamp startDate, Timestamp endDate, Integer clientId, Integer endClientId,
			Integer associateId) {
		super();
		this.placementId = placementId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.clientId = clientId;
		this.endClientId = endClientId;
		this.associateId = associateId;
	}

	public Integer getPlacementId() {
		return placementId;
	}

	public void setPlacementId(Integer placementId) {
		this.placementId = placementId;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getEndClientId() {
		return endClientId;
	}

	public void setEndClientId(Integer endClientId) {
		this.endClientId = endClientId;
	}

	public Integer getAssociateId() {
		return associateId;
	}

	public void setAssociateId(Integer associateId) {
		this.associateId = associateId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + ((endClientId == null) ? 0 : endClientId.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((placementId == null) ? 0 : placementId.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((associateId == null) ? 0 : associateId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Placement other = (Placement) obj;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (endClientId == null) {
			if (other.endClientId != null)
				return false;
		} else if (!endClientId.equals(other.endClientId))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (placementId == null) {
			if (other.placementId != null)
				return false;
		} else if (!placementId.equals(other.placementId))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (associateId == null) {
			if (other.associateId != null)
				return false;
		} else if (!associateId.equals(other.associateId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Placement [placementId=" + placementId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", clientId=" + clientId + ", endClientId=" + endClientId + ", associateId=" + associateId + "]";
	}
	
	
	
}
