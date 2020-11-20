package com.temenos.hackathon.analytica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.temenos.hackathon.analytica.entities.ApiDetails;


public interface ApiDetailsRepositories  extends JpaRepository<ApiDetails, Integer>{


	@Query(value = "Select * from api_details ad where ad.entry_timestamp >= ?1 and ad.entry_timestamp <= ?2" ,nativeQuery = true)
	public List<ApiDetails> getAllApiDetails(String startDate,String endDate);
	
	@Query(value = "Select * from api_details am where am.api_master = ?1",nativeQuery = true)
	public ApiDetails getByApiMaster(Integer id);
	
	@Query(value = "Select * from api_details ad where ad.entry_timestamp >= ?1 and ad.entry_timestamp <= ?2 ORDER BY ad.no_of_hits DESC LIMIT 10" ,nativeQuery = true)
	public List<ApiDetails> getTopTenApiDetails(String startDate,String endDate);
	
	
	@Query(value = "Select * from api_details",nativeQuery = true)
	public List<ApiDetails> getAll();

}

