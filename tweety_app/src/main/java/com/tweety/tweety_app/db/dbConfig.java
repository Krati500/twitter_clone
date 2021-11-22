package com.tweety.tweety_app.db;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
public class dbConfig {
	private Integer connectionPoolSize;
	
	public dbConfig(@Value ("${spring.datasource.maximum-pool-size:10}")
	Integer connectionPoolSize) {
		this.connectionPoolSize = connectionPoolSize;
	}
	
	@Bean
	public Scheduler dbScheduler() {
		return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
	}
}
