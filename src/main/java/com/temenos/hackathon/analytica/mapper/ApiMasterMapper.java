package com.temenos.hackathon.analytica.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.temenos.hackathon.analytica.dto.ApiMasterDTO;
import com.temenos.hackathon.analytica.entities.ApiMaster;

@Component
public class ApiMasterMapper {

	public ApiMasterDTO mapApiMasterEntityToDTO(ApiMaster apiMaster) {
		ApiMasterDTO apiMasterDTO = new ApiMasterDTO();
		apiMasterDTO.setApiName(apiMaster.getApiName());
		apiMasterDTO.setId(apiMaster.getId());
		return apiMasterDTO;	
	}
	
	public ApiMaster mapApiMasterDTOToEntity(ApiMasterDTO apiMasterDTO) {
		ApiMaster apiMaster = new ApiMaster();
		apiMaster.setApiName(apiMasterDTO.getApiName());
		if(apiMasterDTO.getId() != null) {
			apiMaster.setId(apiMasterDTO.getId());
		}
		return apiMaster;	
	}
	
	public List<ApiMasterDTO> mapApiMasterEntityToDTOList(List<ApiMaster> apiMasterList) {
		List<ApiMasterDTO> apiMasterDTOList = new ArrayList<>();
		for(ApiMaster apiMaster : apiMasterList) {
			ApiMasterDTO apiMasteDTO = mapApiMasterEntityToDTO(apiMaster);
			apiMasterDTOList.add(apiMasteDTO);	
		}
		return apiMasterDTOList;	
	}
	
	public List<ApiMaster> mapApiMasterDTOToEntityList(List<ApiMasterDTO> apiMasterDTOList) {
		List<ApiMaster> apiMasterList = new ArrayList<>();
		for(ApiMasterDTO apiMasterDTO : apiMasterDTOList) {
			ApiMaster apiMaster = mapApiMasterDTOToEntity(apiMasterDTO);
			apiMasterList.add(apiMaster);	
		}
		return apiMasterList;	
	}
	
}
