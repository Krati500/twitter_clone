package com.tweety.tweety_app.controller;


import java.security.Principal;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweety.tweety_app.entity.Tweet;
import com.tweety.tweety_app.service.TweetService;
import com.tweety.tweety_app.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tweet")
public class TweetController {
	private final TweetService tweetService;
	private final UserService userService;
	
	public TweetController(TweetService tweetService,UserService userService ) {
		this.tweetService=tweetService;
		this.userService=userService;
		
	}
	
	@PostMapping
	public Mono<Tweet> save (Principal principal, @RequestBody Tweet tweet){
		Mono<com.tweety.tweety_app.entity.User> user = userService.getUserByScreenName(principal.getName());
		return user.flatMap(u -> {
									tweet.setTweetUser(u);
									return tweetService.save(tweet);
		});
	}
	
	@GetMapping
	public Flux<Tweet> getAll(Principal principal){
		return tweetService.getRelevantTweets(principal.getName());
	}

}
