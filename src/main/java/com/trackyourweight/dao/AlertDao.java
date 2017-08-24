package com.trackyourweight.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trackyourweight.model.Alert;

@Repository
public class AlertDao {
	@Autowired	
	private Datastore dataStoreInstance;
	
	public void create(Alert alert) {
		dataStoreInstance.save(alert);
	}
	
	public List<Alert> read() {
		Query<Alert> query = dataStoreInstance.createQuery(Alert.class);
		return query.asList();
	}
	
	public List<Alert> readByTimeRange(long beginTimeStamp,long endTimeStamp) {
		Query<Alert> query = dataStoreInstance.createQuery(Alert.class).field("timeStamp")
							.greaterThanOrEq(beginTimeStamp)
							.field("timeStamp").lessThanOrEq(endTimeStamp);
		return query.asList();
	}
}
