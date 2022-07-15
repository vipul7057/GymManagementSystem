package com.gym.S3Fitness.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource({"classpath:/queries-common.properties"})
public class AppSQLImpl implements AppSQL {
	
	@Autowired
	private Environment ev;
	
	@Override
	public String getSqlQuery(String property) {
		return ev.getProperty(property);
	}

	@Override
	public String getDatabaseProperty(String property) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
