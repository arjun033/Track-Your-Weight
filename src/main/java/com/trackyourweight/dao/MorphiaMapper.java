package com.trackyourweight.dao;

import java.io.IOException;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.trackyourweight.model.Alert;
import com.trackyourweight.model.Metric;
import com.trackyourweight.util.ApplicationProperties;

@Configuration	
public class MorphiaMapper {
	@Autowired
	private MongoClient mongoClient;
	
	@Bean
	public Datastore datastore() throws IOException {
		ApplicationProperties properties = new ApplicationProperties();
		String dbName = properties.getDbName();
		Morphia morphia = new Morphia();
		morphia.map(Metric.class, Alert.class);
		Datastore dataStoreInstance = morphia.createDatastore(mongoClient, dbName);
		
		return dataStoreInstance;
	}
}
