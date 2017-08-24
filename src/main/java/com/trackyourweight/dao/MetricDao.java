package com.trackyourweight.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trackyourweight.model.Metric;

@Repository
public class MetricDao {
	@Autowired	
	private Datastore dataStoreInstance;
	
	public void create(Metric metric) {
		dataStoreInstance.save(metric);
	}
	
	public List<Metric> read() {
		Query<Metric> query = dataStoreInstance.createQuery(Metric.class);
		return query.asList();
	}
	
	public List<Metric> readByTimeRange(long beginTimeStamp, long endTimeStamp) {
		Query<Metric> query = dataStoreInstance.createQuery(Metric.class).field("timeStamp")
							.greaterThanOrEq(beginTimeStamp)
							.field("timeStamp").lessThanOrEq(endTimeStamp);
		return query.asList();
	}
}
