package com.trackyourweight.rule;

import java.io.IOException;

import org.easyrules.annotation.Rule;
import org.easyrules.core.BasicRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trackyourweight.dao.AlertDao;
import com.trackyourweight.model.Alert;
import com.trackyourweight.util.ApplicationProperties;

@Component
@Rule(name="Weight Rule",description="This rule is fired if current weight goes 10% above or below base weight")
public class WeightRule extends BasicRule {
	@Autowired
	protected AlertDao alertDao;
	
	double currentWeight;
	boolean overWeightFlag;
	boolean underWeightFlag;
	ApplicationProperties properties;
	
	@Override
	public boolean evaluate(){
		overWeightFlag=false;
		underWeightFlag=false;
		try {
			overWeightFlag = isOverWeight();
			underWeightFlag = isUnderWeight();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return overWeightFlag || underWeightFlag;
	}
	
	@Override
	public void execute() {
		Alert alert = new Alert();
		String message = "";
		
		if(overWeightFlag)
			message = "OVERWEIGHT ALERT";
		if(underWeightFlag) 
			message = "UNDERWEIGHT ALERT";
		
		alert.setTimeStamp(System.currentTimeMillis());
		alert.setMessage(message);
		alertDao.create(alert);
	}
	
	public void setCurrentWeight(double currentWeight) {
		this.currentWeight = currentWeight;
	}
	
	public boolean isOverWeight() throws IOException {
		if(properties==null) properties = new ApplicationProperties();
		double baseWeight = properties.getBaseWeight();
		double weightThreshold = 1.1*baseWeight;
		if(currentWeight>=weightThreshold)
			return true;
		return false;
	}
	
	public boolean isUnderWeight() throws IOException {
		if(properties==null) properties = new ApplicationProperties();
		double baseWeight = properties.getBaseWeight();
		double weightThreshold = 0.9*baseWeight;
		if(currentWeight<=weightThreshold)
			return true;
		return false;
	}
}
