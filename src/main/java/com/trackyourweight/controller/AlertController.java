package com.trackyourweight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trackyourweight.model.Alert;
import com.trackyourweight.service.AlertService;

@RestController
@RequestMapping(value="/alert")
public class AlertController {
	@Autowired
	private AlertService alertService;
	
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public ResponseEntity<List<Alert>> read() {
		List<Alert> alerts = alertService.read();
		if(alerts==null || alerts.size()==0){
			return new ResponseEntity<List<Alert>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Alert>>(alerts, HttpStatus.OK);
	}
	
	@RequestMapping(value="/readByTimeRange", method = RequestMethod.GET)
	public ResponseEntity<List<Alert>> readByTimeRange(@RequestParam long beginTimeStamp, 
			@RequestParam long endTimeStamp) {
		List<Alert> alerts = alertService.readByTimeRange(beginTimeStamp, endTimeStamp);
		if(alerts==null || alerts.size()==0){
			return new ResponseEntity<List<Alert>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Alert>>(alerts, HttpStatus.OK);
	}
}
