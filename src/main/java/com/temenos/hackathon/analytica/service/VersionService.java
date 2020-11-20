package com.temenos.hackathon.analytica.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/v0.1/api")
public class VersionService {

	
	@RequestMapping(value = "/version", method = RequestMethod.GET)
    @ResponseBody
	public String getVersion() {
		return "v0.1";
	}
}
