package com.tweety.tweety_app.entity;

import java.security.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.catalina.User;
import org.hibernate.annotations.CreationTimestamp;
import javax.validation.constraints.NotNull;

@Entity
public class TweetEnitity {
	@Id
	private Integer id;
	
	@CreationTimestamp
	private Timestamp postTime;
	
	@ManyToOne
	private User tweetUser;
	
	@NotNull
	private String content;
	
	
  
}
