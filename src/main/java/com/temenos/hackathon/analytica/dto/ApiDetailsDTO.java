package com.temenos.hackathon.analytica.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ApiDetailsDTO {
	Integer id;
	Integer noOfHits;
	Date entryTimeStamp;
	ApiMasterDTO apiMaster;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNoOfHits() {
		return noOfHits;
	}
	public void setNoOfHits(Integer noOfHits) {
		this.noOfHits = noOfHits;
	}
	public Date getEntryTimeStamp() {
		return entryTimeStamp;
	}
	public void setEntryTimeStamp(Date entryTimeStamp) {
		this.entryTimeStamp = entryTimeStamp;
	}
	public ApiMasterDTO getApiMaster() {
		return apiMaster;
	}
	public void setApiMaster(ApiMasterDTO apiMaster) {
		this.apiMaster = apiMaster;
	}
	
	
}
