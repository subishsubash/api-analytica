package com.temenos.hackathon.analytica.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.temenos.hackathon.analytica.dto.ApiMasterDTO;
import com.temenos.hackathon.analytica.entities.ApiMaster;
import com.temenos.hackathon.analytica.mapper.ApiMasterMapper;
import com.temenos.hackathon.analytica.repositories.ApiMasterRepositories;

@Repository
public class ApiMasterDao {

	@Autowired
	private ApiMasterMapper apiMasterMapper;
	
	@Autowired
	private ApiMasterRepositories apiMasterRepositories;
	
	public List<ApiMasterDTO> getAllApiMaster(){
		List<ApiMaster> apiMasterList = apiMasterRepositories.getAllApiMaster();
		return apiMasterMapper.mapApiMasterEntityToDTOList(apiMasterList);
	}
	
	public ApiMasterDTO saveApiMaster(ApiMasterDTO apiMasterDTO) throws Exception{	

		ApiMaster apiMaster = apiMasterMapper.mapApiMasterDTOToEntity(apiMasterDTO);
		ApiMaster apiMasterTest = apiMasterRepositories.getByName(apiMaster.getApiName()); 
		if(apiMasterTest == null) {
			apiMasterRepositories.save(apiMaster);
			apiMasterTest = apiMasterRepositories.getByName(apiMaster.getApiName());
		
		}
		return apiMasterMapper.mapApiMasterEntityToDTO(apiMasterTest);
		
	}
}

