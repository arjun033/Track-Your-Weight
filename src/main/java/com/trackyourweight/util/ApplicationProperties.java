package com.trackyourweight.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {
	private String dbUrl;
	private String dbPort;
	private String dbName;
	private static Properties properties;

	public String getDbUrl() throws IOException {
		if(properties==null) readPropertiesFile();
		String dbUrl = properties.getProperty("db.url");
		return dbUrl;
	}
	public String getDbPort() throws IOException {
		if(properties==null) readPropertiesFile();
		String dbPort = properties.getProperty("db.port");
		return dbPort;
	}
	public String getDbName() throws IOException {
		if(properties==null) readPropertiesFile();
		String dbName = properties.getProperty("db.name");
		return dbName;
	}
	public double getWeightLowerBound() throws IOException {
		if(properties==null) readPropertiesFile();
		String weightLowerBound = properties.getProperty("application.weightlowerbound");
		return Double.parseDouble(weightLowerBound);
	}
	public double getWeightUpperBound() throws IOException {
		if(properties==null) readPropertiesFile();
		String weightUpperBound = properties.getProperty("application.weightupperbound");
		return Double.parseDouble(weightUpperBound);
	}
	public double getBaseWeight() throws IOException {
		if(properties==null) readPropertiesFile();
		String baseWeight = properties.getProperty("application.baseweight");
		return Double.parseDouble(baseWeight);
	}
	public void readPropertiesFile() throws IOException {
		properties = new Properties();
		String propertiesFile = "application.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFile);
		if(inputStream!=null) properties.load(inputStream);
	}
}
