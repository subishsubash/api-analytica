package com.temenos.hackathon.analytica.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Util {
	
	public Date dateFormaterStringToDate(String dateString) {
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date date = formatter.parse(dateString);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		 
	}
	

}
