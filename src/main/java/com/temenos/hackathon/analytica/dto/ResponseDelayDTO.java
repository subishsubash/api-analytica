package com.temenos.hackathon.analytica.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ResponseDelayDTO {

	Integer id;
	String delayTime;
	Date entryTimeStamp;
	ApiMasterDTO apiMaster;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(String delayTime) {
		this.delayTime = delayTime;
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
