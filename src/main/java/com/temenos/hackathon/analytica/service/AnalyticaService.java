package com.temenos.hackathon.analytica.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.temenos.hackathon.analytica.dto.AnalyticaResponse;
import com.temenos.hackathon.analytica.process.AnalyticaProcess;

@Controller
@RequestMapping("/v0.1/api")
public class AnalyticaService {

	 public AnalyticaResponse throwErrorOnCatch(Exception ex){
	        System.out.println(ex);
	        AnalyticaResponse analyticaResponse = new AnalyticaResponse("Error",ex.toString());
	        return analyticaResponse;
	 }

	 public AnalyticaResponse successResponse(Object response){
	 	AnalyticaResponse analyticaResponse = new AnalyticaResponse("Success",response);
	     return analyticaResponse;
	 }
 
	@Autowired
	private AnalyticaProcess analyticaProcess;
	
	/*
	 * UI call to load the High charts - Specific time
	 * @param timeline {1d,1w,1m}
	 * @param AnalyticaResponse
	 * 
	 */
	@RequestMapping(value = "/graphdata/{timeLine}", method = RequestMethod.GET)
    @ResponseBody
	public AnalyticaResponse getGraphSpecificTimeLine(@PathVariable("timeLine") String timeLine) {
		JSONObject response  = new JSONObject();
		try {
			response = analyticaProcess.getGraphDataForSpecificTimeLine(timeLine);
			
		}catch(Exception ex) {
			return throwErrorOnCatch(ex);
		}
		return successResponse(response);
	}
	
	/* 
	 *Call from IRIS to store details in Analtica Database
	 *
	 */
	@RequestMapping(value = "/entry",method = RequestMethod.POST)
	@ResponseBody
	public AnalyticaResponse createEntry(@RequestBody JSONObject jsonObject) {
		String response  = new String();
		try {
			response = analyticaProcess.createEntry(jsonObject);
			
		}catch(Exception ex) {
			return throwErrorOnCatch(ex);
		}
		return successResponse(response);	
	}
	
	/*
	 * Fetch the graphdata for Hourly details, Irrespective of API names 
	 * 
	 */
	 @RequestMapping(value = "/graphdatahourly",method = RequestMethod.GET)
	 @ResponseBody
	 public AnalyticaResponse getHourlyDetails(@RequestParam("from") String fromDate,@RequestParam("to") String toDate) {
		 JSONObject response  = new JSONObject();
			try {
				response = analyticaProcess.getHourlyDetails(fromDate,toDate);
				
			}catch(Exception ex) {
				return throwErrorOnCatch(ex);
			}
			return successResponse(response);	
	 }
	 
		/*
		 * Fetch the graphdata for day wise details, Irrespective of API names 
		 * 
		 */
		 @RequestMapping(value = "/graphdatadays/{noOfweeks}",method = RequestMethod.GET)
		 @ResponseBody
		 public AnalyticaResponse getDaysDetails(@PathVariable("noOfweeks") String noOfweeks) {
			 JSONObject response  = new JSONObject();
				try {
					response = analyticaProcess.getDaysDetails(noOfweeks);
					
				}catch(Exception ex) {
					return throwErrorOnCatch(ex);
				}
				return successResponse(response);	
		 }
	
	
}
