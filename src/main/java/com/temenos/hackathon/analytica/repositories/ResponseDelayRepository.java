package com.temenos.hackathon.analytica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.temenos.hackathon.analytica.entities.ResponseDelay;

public interface ResponseDelayRepository extends JpaRepository<ResponseDelay,Integer>{

	@Query(value = "SELECT id,api_master,AVG(delay_time) AS delay_time,entry_timestamp from response_delay rd WHERE rd.entry_timestamp >= ?1 AND rd.entry_timestamp <= ?2 GROUP BY api_master",nativeQuery = true)
	public List<ResponseDelay> getAllApiDelayTime(String startDate,String endDate);
	
	@Query(value = "SELECT * from response_delay",nativeQuery = true)
	public List<ResponseDelay> getAll();

	@Query(value = "SELECT * from response_delay rd WHERE rd.entry_timestamp >= ?1 AND rd.entry_timestamp <= ?2",nativeQuery = true)
	public List<ResponseDelay> getAllFromToDate(String startDate,String endDate);
	
}
