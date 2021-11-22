package com.tweety.tweety_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweety.tweety_app.entity.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer> {
	List<Tweet> findByTweetUser_ScreenNameOrContentContains(String screenName, String mention);
}
