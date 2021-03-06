package com.tracebucket.tron.test.mvc.controller;

import com.tracebucket.tron.endpoint.OverrideRequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ffl on 14-04-2015.
 */
@RestController
public class ApplicationController {

	@OverrideRequestMapping
	@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
	public String test(){
		return "Application endpoint";
	}
}
