package com.trackyourweight.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackyourweight.dao.AlertDao;
import com.trackyourweight.model.Alert;

@Service
public class AlertService {

	@Autowired
	private AlertDao alertDao;

	public List<Alert> read() {
		return alertDao.read();
	}

	public List<Alert> readByTimeRange(long beginTimeStamp,long endTimeStamp) {
		return alertDao.readByTimeRange(beginTimeStamp, endTimeStamp);
	}	
}
