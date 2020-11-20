package com.temenos.hackathon.analytica.process;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.temenos.hackathon.analytica.dao.ApiDetailDao;
import com.temenos.hackathon.analytica.dao.ApiMasterDao;
import com.temenos.hackathon.analytica.dao.ResponseDelayDao;
import com.temenos.hackathon.analytica.dto.ApiDetailsDTO;
import com.temenos.hackathon.analytica.dto.ApiMasterDTO;
import com.temenos.hackathon.analytica.dto.ResponseDelayDTO;
import com.temenos.hackathon.analytica.util.Util;


@Transactional
@Component
public class AnalyticaProcess {

	@Autowired
	private ApiMasterDao apiMasteDao;
	
	@Autowired
	private ApiDetailDao apiDetailDao;
	
	@Autowired
	private ResponseDelayDao responseDelyaDao;
	
	@Autowired
	private Util util;

	@SuppressWarnings("unchecked")	
	public JSONObject getGraphDataForSpecificTimeLine(String timeLine) throws Exception{
		JSONObject response = new JSONObject();
		String startDate = new String() ;
		String endDate = new String();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		  
		if(timeLine.charAt(1) == 'd') {
			LocalDate onedate = LocalDate.now().minusDays(timeLine.charAt(0));
			startDate = formatter.format(onedate);
			endDate = formatter.format(LocalDate.now().plusDays(1));
			
		} else if(timeLine.charAt(1) == 'w') {
			int week = Character.getNumericValue(timeLine.charAt(0))*7;
			LocalDate oneWeekdate = LocalDate.now().minusDays(week);
			startDate = formatter.format(oneWeekdate);
			endDate = formatter.format(LocalDate.now().plusDays(1));
		} else if(timeLine.charAt(1) == 'm') {
			int month = Character.getNumericValue(timeLine.charAt(0))*30;
			LocalDate oneMonthdate = LocalDate.now().minusDays(month);
			startDate = formatter.format(oneMonthdate);
			endDate = formatter.format(LocalDate.now().plusDays(1));
		} else if(timeLine.length() == 4) {
			return getGraphDataForYears(timeLine);
		} else {
			throw new Exception("Input valid timeline {1d,1w,1m,yyyy}")	;
			}
		
		
		List<ApiDetailsDTO> apiDetailsDTOList = apiDetailDao.getTopTenApiDetails(startDate,endDate);
		List<ResponseDelayDTO> reponseDelayDTOList = responseDelyaDao.getAllApiDelayTime(startDate, endDate);
		JSONArray apiDetailsArray = new JSONArray();
			
		for(ApiDetailsDTO apiDetailsDTO : apiDetailsDTOList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("apiName",apiDetailsDTO.getApiMaster().getApiName());
			jsonObject.put("noOfHits",apiDetailsDTO.getNoOfHits());
			for(ResponseDelayDTO resonseDelayDTO : reponseDelayDTOList) {
				if(apiDetailsDTO.getApiMaster().getApiName().equals(resonseDelayDTO.getApiMaster().getApiName())) {
					jsonObject.put("averageResponseDelay",resonseDelayDTO.getDelayTime());
				}
			}
			apiDetailsArray.add(jsonObject);
		}
		response.put("apiDetails",apiDetailsArray);
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getGraphDataForYears(String timeLine) {
		JSONObject response = new JSONObject();
		String startDate = new String() ;
		String endDate = new String();
		if(timeLine.equals("2019")) {
			startDate = "2019-01-01";
			endDate = "2019-12-31";
		}else if (timeLine.equals("2018")) {
			startDate = "2018-01-01";
			endDate = "2018-12-31";
		}else if (timeLine.equals("2017")) {
			startDate = "2017-01-01";
			endDate = "2017-12-31";
		}else if (timeLine.equals("2016")) {
			startDate = "2016-01-01";
			endDate = "2016-12-31";
		}else if (timeLine.equals("2015")) {
			startDate = "2015-01-01";
			endDate = "2015-12-31";
		}
		List<ApiDetailsDTO> apiDetailsDTOList = apiDetailDao.getTopTenApiDetails(startDate, endDate);
		JSONArray jsonArray = new JSONArray();
		for(ApiDetailsDTO apiDetailsDTO :apiDetailsDTOList ) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("apiName",apiDetailsDTO.getApiMaster().getApiName());
			jsonObject.put("noOfHits",apiDetailsDTO.getNoOfHits());
			jsonArray.add(jsonObject);
		}
		response.put(timeLine,jsonArray);
		return response;
	}
	
	protected List<ApiMasterDTO> getAllApiMaster(){
		return apiMasteDao.getAllApiMaster();
	}

	public String createEntry(JSONObject jsonObject) throws Exception {
		
		String apiName = (String) jsonObject.get("apiName");
		String delayTime = (String) jsonObject.get("responseDelay");
		String requestDate = (String) jsonObject.get("requestDate");
		ApiMasterDTO apiMasterDTO = new ApiMasterDTO();
		ResponseDelayDTO responseDelayDTO = new ResponseDelayDTO();
		apiMasterDTO.setApiName(apiName);
		ApiMasterDTO apiMasterDTOGotByName = apiMasteDao.saveApiMaster(apiMasterDTO);
		String apiDetailsStatus = new String();
		ApiDetailsDTO apiDetailsDTO = new ApiDetailsDTO();
		apiDetailsDTO.setApiMaster(apiMasterDTOGotByName);
		apiDetailsDTO.setEntryTimeStamp(util.dateFormaterStringToDate(requestDate));
		apiDetailsStatus = apiDetailDao.saveApiDetails(apiDetailsDTO);
		responseDelayDTO.setApiMaster(apiMasterDTOGotByName);
		responseDelayDTO.setDelayTime(delayTime);
		responseDelayDTO.setEntryTimeStamp(util.dateFormaterStringToDate(requestDate));
		String reponseDelayStatus = "Records are not saved successfully";
		if(apiDetailsStatus == "Success") {
			reponseDelayStatus = responseDelyaDao.saveNewReponseDelay(responseDelayDTO);
		}
		return reponseDelayStatus;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getHourlyDetails(String fromDate, String toDate) throws Exception{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(toDate,formatter);
		localDate = localDate.plusDays(1);
		toDate = localDate.format(formatter);
		List<ResponseDelayDTO> responseDelayDTOList = responseDelyaDao.getAllApiDelay(fromDate,toDate);
		Integer first =0,second =0,third=0,fourth=0,fifth=0,sixth=0; 
		for(ResponseDelayDTO responseDelayDTO : responseDelayDTOList) {
			Date date =  responseDelayDTO.getEntryTimeStamp();
			Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
			calendar.setTime(date);
			int hour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
			if(	0<=hour && hour<=3) {
				first++;
			}
			else if(4<=hour && hour<=7) {
				second++;
			}
			else if(8<=hour && hour<=11) {
				third++;
				
			}
			else if(12<=hour && hour<=15) {
				fourth++;
				
			} 
			else if(16<=hour && hour<=19) {
				fifth++;
				
			}
			else if(20<=hour && hour<=23) {
				sixth++;
				
			}
			
		}
		JSONObject response =  new JSONObject();
		response.put("00:00-03:59", first);
		response.put("04:00-07:59", second);
		response.put("08:00-11:59", third);
		response.put("12:00-15:59", fourth);
		response.put("16:00-19:59", fifth);
		response.put("20:00-23:59", sixth);
		return response;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDaysDetails(String noOfweeks) throws Exception {
		String startDate = new String() ;
		String endDate = new String();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		JSONObject resposne = new JSONObject();
		if(noOfweeks.charAt(1) == 'w') {
			int week = Character.getNumericValue(noOfweeks.charAt(0))*7;
			LocalDate oneWeekdate = LocalDate.now().minusDays(week);
			startDate = formatter.format(oneWeekdate);
			endDate = formatter.format(LocalDate.now());
		} else {
			throw new Exception("Input valid no of week {1w,2w.}")	;
		}
		
		List<ResponseDelayDTO> responseDelayDTOList = responseDelyaDao.getAllApiDelay(startDate,endDate);
		Integer monday=0,tuesday=0,wednesday=0,thursday=0, friday=0 ,saturday=0,sunday=0;
		
		for(ResponseDelayDTO responseDelayDTO : responseDelayDTOList) {
			Date date =  responseDelayDTO.getEntryTimeStamp();
			Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
			calendar.setTime(date);
			int day = calendar.get(Calendar.DAY_OF_WEEK); // the day of the week in numerical format
			switch(day) {
				case 2:
					monday++;
					break;
				case 3:
					tuesday++;
					break;
				case 4:
					wednesday++;
					break;
				case 5:
					thursday++;
					break;
				case 6:
					friday++;
					break;
				case 7:
					saturday++;
					break;
				case 1:
					sunday++;
					break;		
			}	
			
		}
		
		resposne.put("monday", monday);
		resposne.put("tuesday", tuesday);
		resposne.put("wednesday", wednesday);
		resposne.put("thursday", thursday);
		resposne.put("friday", friday);
		resposne.put("saturday", saturday);
		resposne.put("sunday", sunday);
		return resposne;
	}
	
	
	
}