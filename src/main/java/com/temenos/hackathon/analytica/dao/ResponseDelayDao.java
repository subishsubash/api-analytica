package com.temenos.hackathon.analytica.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.temenos.hackathon.analytica.dto.ResponseDelayDTO;
import com.temenos.hackathon.analytica.entities.ResponseDelay;
import com.temenos.hackathon.analytica.mapper.ResponseDelayMapper;
import com.temenos.hackathon.analytica.repositories.ResponseDelayRepository;

@Repository
public class ResponseDelayDao {

	@Autowired
	private ResponseDelayMapper responseDelayMapper;
	
	@Autowired
	private ResponseDelayRepository responseDelayRepository;
	
	public List<ResponseDelayDTO> getAllApiDelayTime(String startDate,String endDate) {
		List<ResponseDelay> responseDelayList = responseDelayRepository.getAllApiDelayTime(startDate,endDate);
		return responseDelayMapper.mapResponseDelayEntityToDTOList(responseDelayList);
	}
	
	public List<ResponseDelayDTO> getAllApiDelay(String fromDate,String toDate) {
		List<ResponseDelay> responseDelayList = responseDelayRepository.getAllFromToDate(fromDate,toDate);
		return responseDelayMapper.mapResponseDelayEntityToDTOList(responseDelayList);
	}

	public String saveNewReponseDelay(ResponseDelayDTO responseDelayDTO) {
		ResponseDelay responseDelay = responseDelayMapper.mapResponseDelayDTOToEntity(responseDelayDTO);
		try {
			responseDelayRepository.save(responseDelay);
		}catch(Exception ex){
			return "Records are not saved successfully" ;
		}
		return "Records are saved successfully";
	}
	
	
}
