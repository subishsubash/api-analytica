package com.temenos.hackathon.analytica.dto;

import org.springframework.stereotype.Component;

@Component
public class ApiMasterDTO {
	Integer id;
	String apiName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	
}
