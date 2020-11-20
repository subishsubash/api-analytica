package com.temenos.hackathon.analytica.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.temenos.hackathon.analytica.dto.ApiDetailsDTO;
import com.temenos.hackathon.analytica.entities.ApiDetails;
import com.temenos.hackathon.analytica.mapper.ApiDetailsMapper;
import com.temenos.hackathon.analytica.repositories.ApiDetailsRepositories;

@Repository
public class ApiDetailDao {

	
	@Autowired
	private ApiDetailsMapper apiDetailsMapper;
	
	@Autowired
	private ApiDetailsRepositories apiDetailsRepositories;
	
	public List<ApiDetailsDTO> getAllApiDetails(String startDate,String endDate){
		List<ApiDetails> apiDetailList = apiDetailsRepositories.getAllApiDetails(startDate,endDate);
		return apiDetailsMapper.mapApiDetailsEntityToDTOList(apiDetailList);
	}
	
	public List<ApiDetailsDTO> getTopTenApiDetails(String startDate,String endDate){
		List<ApiDetails> apiDetailList = apiDetailsRepositories.getTopTenApiDetails(startDate,endDate);
		return apiDetailsMapper.mapApiDetailsEntityToDTOList(apiDetailList);
	}
	
	public List<ApiDetailsDTO> getAll(){
		List<ApiDetails> apiDetailList = apiDetailsRepositories.getAll();
		return apiDetailsMapper.mapApiDetailsEntityToDTOList(apiDetailList);
	}
	
	public ApiDetailsDTO getByMasterId(Integer masterId)  {
		ApiDetails apiDetail = apiDetailsRepositories.getByApiMaster(masterId);
		return apiDetailsMapper.mapApiDetailsEntityToDTO(apiDetail);
	}

	public String saveApiDetails(ApiDetailsDTO apiDetailsDTO) {
		ApiDetails apiDetails = apiDetailsRepositories.getByApiMaster(apiDetailsDTO.getApiMaster().getId());
		try {
			if(apiDetails == null) {
				apiDetails = apiDetailsMapper.mapApiDetailsDTOToEntity(apiDetailsDTO);
				apiDetails.setNoOfHits(1);
			}else {
				Integer noOfHits = apiDetails.getNoOfHits();
				apiDetails.setEntryTimestamp(apiDetailsDTO.getEntryTimeStamp());
				apiDetails.setNoOfHits(noOfHits+1);
			}
			apiDetailsRepositories.save(apiDetails);
		}catch(Exception ex) {
			return "Failure";
		}
		return "Success";
	}
	
	
}
