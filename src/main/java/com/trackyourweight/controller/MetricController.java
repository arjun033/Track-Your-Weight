package com.trackyourweight.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trackyourweight.exception.WeightAnomalyException;
import com.trackyourweight.model.Metric;
import com.trackyourweight.service.MetricService;

@RestController
@RequestMapping(value="/metric")
public class MetricController {
	@Autowired
	private MetricService metricService;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)		
	public ResponseEntity<String> create(@RequestBody Metric metric) {
		try {
			metricService.create(metric);
		} catch (IOException | WeightAnomalyException e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public ResponseEntity<List<Metric>> read() {
		List<Metric> metrics = metricService.read();
		if(metrics==null || metrics.size()==0){
			return new ResponseEntity<List<Metric>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Metric>>(metrics, HttpStatus.OK);
	}
	
	@RequestMapping(value="/readByTimeRange", method=RequestMethod.GET)
	public ResponseEntity<List<Metric>> readByTimeRange(@RequestParam long beginTimeStamp, 
			@RequestParam long endTimeStamp) {
		List<Metric> metrics = metricService.readByTimeRange(beginTimeStamp, endTimeStamp);
		if(metrics==null || metrics.size()==0){
			return new ResponseEntity<List<Metric>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Metric>>(metrics, HttpStatus.OK);
	}
}
