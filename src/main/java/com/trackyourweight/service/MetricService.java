package com.trackyourweight.service;

import java.io.IOException;
import java.util.List;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackyourweight.dao.MetricDao;
import com.trackyourweight.exception.WeightAnomalyException;
import com.trackyourweight.model.Metric;
import com.trackyourweight.rule.WeightRule;
import com.trackyourweight.util.ApplicationProperties;

@Service
public class MetricService {
	
	@Autowired
	private MetricDao metricDao;
	@Autowired
	private WeightRule weightRule;
	
	ApplicationProperties properties;
	
	public void create(Metric metric) throws IOException, WeightAnomalyException {
		if(!isValidWeight(metric))
			throw new WeightAnomalyException("Weight is not in acceptable range");
		else {
			//Checking for rules
			RulesEngineBuilder builder = RulesEngineBuilder.aNewRulesEngine();
			RulesEngine rulesEngine = builder.build();
			weightRule.setCurrentWeight(metric.getValue());
			rulesEngine.registerRule(weightRule);
			rulesEngine.fireRules();
		}
		metricDao.create(metric);
	}

	public List<Metric> read() {
		return metricDao.read();
	}
	
	public List<Metric> readByTimeRange(long beginTimeStamp,long endTimeStamp) {
		return metricDao.readByTimeRange(beginTimeStamp,endTimeStamp);
	}
	
	public boolean isValidWeight(Metric metric) throws IOException {
		if(properties==null) properties = new ApplicationProperties();
		double weightLowerBound = properties.getWeightLowerBound();
		double weightUpperBound = properties.getWeightUpperBound();
		if(metric.getValue()<weightLowerBound || metric.getValue()>weightUpperBound)
			return false;
		return true;
	}
	
}

