package com.temenos.hackathon.analytica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.temenos.hackathon.analytica.entities.ApiMaster;

public interface ApiMasterRepositories extends JpaRepository<ApiMaster, Integer> {

		
	@Query(value = "Select * from api_master",nativeQuery = true)
	public List<ApiMaster> getAllApiMaster();
	
	@Query(value = "Select * from api_master am where am.api_name = ?1",nativeQuery = true)
	public ApiMaster getByName(String name);
}
