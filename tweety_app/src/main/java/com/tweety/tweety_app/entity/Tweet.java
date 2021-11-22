package com.tweety.tweety_app.entity;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import org.hibernate.annotations.CreationTimestamp;
import javax.validation.constraints.NotNull;
import com.tweety.tweety_app.entity.User;



@Entity
public class Tweet {
	@Id
	private Integer id;
	
	@CreationTimestamp
	private Timestamp postTime;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getPostTime() {
		return postTime;
	}

	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getTweetUser() {
		return tweetUser;
	}

	@ManyToOne
	private User tweetUser;
	
	@NotNull
	private String content;

	public void setTweetUser(User user) {
		this.tweetUser = user;
		
	}
	
	
  
}
