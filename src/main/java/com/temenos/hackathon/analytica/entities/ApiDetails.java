package com.temenos.hackathon.analytica.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the api_details database table.
 * 
 */
@Entity
@Table(name="api_details")
@NamedQuery(name="ApiDetail.findAll", query="SELECT a FROM ApiDetails a")
public class ApiDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name="entry_timestamp")
	private Date entryTimestamp;

	@Column(name="no_of_hits")
	private Integer noOfHits;

	//bi-directional many-to-one association to ApiMaster
	@ManyToOne
	@JoinColumn(name="api_master")
	private ApiMaster apiMasterBean;

	public ApiDetails() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEntryTimestamp() {
		return this.entryTimestamp;
	}

	public void setEntryTimestamp(Date entryTimestamp) {
		this.entryTimestamp = entryTimestamp;
	}

	public Integer getNoOfHits() {
		return this.noOfHits;
	}

	public void setNoOfHits(Integer noOfHits) {
		this.noOfHits = noOfHits;
	}

	public ApiMaster getApiMasterBean() {
		return this.apiMasterBean;
	}

	public void setApiMasterBean(ApiMaster apiMasterBean) {
		this.apiMasterBean = apiMasterBean;
	}

}