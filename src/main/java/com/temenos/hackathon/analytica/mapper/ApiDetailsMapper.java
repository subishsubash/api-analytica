package com.temenos.hackathon.analytica.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.temenos.hackathon.analytica.dto.ApiDetailsDTO;
import com.temenos.hackathon.analytica.entities.ApiDetails;

@Component
public class ApiDetailsMapper {
	
	@Autowired
	private ApiMasterMapper apiMasterMapper; 
	
	public ApiDetails mapApiDetailsDTOToEntity(ApiDetailsDTO apiDetailDTO) {
		ApiDetails apiDetail = new ApiDetails();
		apiDetail.setApiMasterBean(apiMasterMapper.mapApiMasterDTOToEntity(apiDetailDTO.getApiMaster()));
		apiDetail.setEntryTimestamp(apiDetailDTO.getEntryTimeStamp());
		if(apiDetailDTO.getId() != null){
			apiDetail.setId(apiDetailDTO.getId());
		}
		apiDetail.setNoOfHits(apiDetail.getNoOfHits());
		return apiDetail;
	}
	
	
	public ApiDetailsDTO mapApiDetailsEntityToDTO(ApiDetails apiDetail) {
		ApiDetailsDTO apiDetailDTO = new ApiDetailsDTO();
		apiDetailDTO.setApiMaster(apiMasterMapper.mapApiMasterEntityToDTO(apiDetail.getApiMasterBean()));
		apiDetailDTO.setEntryTimeStamp(apiDetail.getEntryTimestamp());
		apiDetailDTO.setId(apiDetail.getId());
		apiDetailDTO.setNoOfHits(apiDetail.getNoOfHits());
		return apiDetailDTO;
	}
	
	public List<ApiDetails> mapApiDetailsDTOToEntityList(List<ApiDetailsDTO> apiDetailDTOList) {
		List<ApiDetails> apiDetailList = new ArrayList<>();
		for(ApiDetailsDTO apiDetailDTO : apiDetailDTOList) {
			ApiDetails apiDetail = mapApiDetailsDTOToEntity(apiDetailDTO);
			apiDetailList.add(apiDetail);
		}
		return apiDetailList;
	}
	
	public List<ApiDetailsDTO> mapApiDetailsEntityToDTOList(List<ApiDetails> apiDetailList) {
		List<ApiDetailsDTO> apiDetailDTOList = new ArrayList<>();
		for(ApiDetails apiDetail : apiDetailList) {
			ApiDetailsDTO apiDetailDTO = mapApiDetailsEntityToDTO(apiDetail);
			apiDetailDTOList.add(apiDetailDTO);
		}
		return apiDetailDTOList;
	}

}
