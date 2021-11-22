package com.tweety.tweety_app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tweety.tweety_app.entity.Tweet;
import com.tweety.tweety_app.repository.TweetRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@Transactional(readOnly = true)
public class TweetService {
	
	public final TweetRepository tweetRepository;
	public final Scheduler dbScheduler;
	
	public TweetService (TweetRepository tweetRepository, Scheduler dbScheduler ) {
		this.tweetRepository=tweetRepository;
		this.dbScheduler = dbScheduler;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Mono<? extends Tweet> save(Tweet tweet) {
		return Mono.fromCallable(()->tweetRepository.save(tweet)).publishOn(dbScheduler);
	}
	
	public Flux<Tweet> getTweets(){
		return Flux.fromIterable(tweetRepository.findAll()).publishOn(dbScheduler);
	}

	public Flux<Tweet> getRelevantTweets(String screenName) {
		return Flux.fromIterable(tweetRepository.findByTweetUser_ScreenNameOrContentContains
				(screenName, "@"+screenName)).publishOn(dbScheduler);
				
	}

}
