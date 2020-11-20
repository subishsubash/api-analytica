package com.temenos.hackathon.analytica.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.temenos.hackathon.analytica.dto.ResponseDelayDTO;
import com.temenos.hackathon.analytica.entities.ResponseDelay;

@Component
public class ResponseDelayMapper {


	@Autowired
	private ApiMasterMapper apiMasterMapper;
	
	public ResponseDelayDTO  mapResponseDelayEntityToDTO(ResponseDelay responseDelay) {
		ResponseDelayDTO  responseDelayDTO = new ResponseDelayDTO();
		responseDelayDTO.setApiMaster(apiMasterMapper.mapApiMasterEntityToDTO(responseDelay.getApiMasterBean()));
		responseDelayDTO.setDelayTime(responseDelay.getDelayTime());
		responseDelayDTO.setEntryTimeStamp(responseDelay.getEntryTimestamp());
		responseDelayDTO.setId(responseDelay.getId());
		return responseDelayDTO;
	}
	
	public ResponseDelay  mapResponseDelayDTOToEntity(ResponseDelayDTO responseDelayDTO) {
		ResponseDelay  responseDelay = new ResponseDelay();
		responseDelay.setApiMasterBean(apiMasterMapper.mapApiMasterDTOToEntity(responseDelayDTO.getApiMaster()));
		responseDelay.setDelayTime(responseDelayDTO.getDelayTime());
		responseDelay.setEntryTimestamp(responseDelayDTO.getEntryTimeStamp());
		if(responseDelayDTO.getId() != null) {
			responseDelay.setId(responseDelayDTO.getId());
		}
		return responseDelay;
	}
	
	
	public List<ResponseDelayDTO>  mapResponseDelayEntityToDTOList(List<ResponseDelay> responseDelayList) {
		List<ResponseDelayDTO>  responseDelayDTOList = new ArrayList<>();
		for(ResponseDelay responseDelay : responseDelayList) {
			ResponseDelayDTO reponseDelayDTO = mapResponseDelayEntityToDTO(responseDelay);
			responseDelayDTOList.add(reponseDelayDTO);
		}
		return responseDelayDTOList;
	}

	public List<ResponseDelay>  mapResponseDelayDTOToEntityList(List<ResponseDelayDTO> responseDelayDTOList) {
		List<ResponseDelay>  responseDelayList = new ArrayList<>();
		for(ResponseDelayDTO responseDelayDTO : responseDelayDTOList) {
			ResponseDelay reponseDelay = mapResponseDelayDTOToEntity(responseDelayDTO);
			responseDelayList.add(reponseDelay);
		}
		return responseDelayList;
	}
	
}

