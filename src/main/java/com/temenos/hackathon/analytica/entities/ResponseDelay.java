package com.temenos.hackathon.analytica.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the response_delay database table.
 * 
 */
@Entity
@Table(name="response_delay")
@NamedQuery(name="ResponseDelay.findAll", query="SELECT r FROM ResponseDelay r")
public class ResponseDelay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name="delay_time")
	private String delayTime;

	@Column(name="entry_timestamp")
	private Date entryTimestamp;

	//bi-directional many-to-one association to ApiMaster
	@ManyToOne
	@JoinColumn(name="api_master")
	private ApiMaster apiMasterBean;

	public ResponseDelay() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDelayTime() {
		return this.delayTime;
	}

	public void setDelayTime(String delayTime) {
		this.delayTime = delayTime;
	}

	public Date getEntryTimestamp() {
		return this.entryTimestamp;
	}

	public void setEntryTimestamp(Date entryTimestamp) {
		this.entryTimestamp = entryTimestamp;
	}

	public ApiMaster getApiMasterBean() {
		return this.apiMasterBean;
	}

	public void setApiMasterBean(ApiMaster apiMasterBean) {
		this.apiMasterBean = apiMasterBean;
	}

}