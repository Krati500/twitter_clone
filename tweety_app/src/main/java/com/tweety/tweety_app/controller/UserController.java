package com.tweety.tweety_app.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import com.tweety.tweety_app.entity.User;
import com.tweety.tweety_app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/{screenName}")
	public Mono<User> getUserByScreenName(@PathVariable String screenName){
		return userService.getUserByScreenName(screenName);
	}
	
	@PutMapping("/{userId}/follow")
	@ResponseStatus(code = HttpStatus.OK)
	public void folowUser(Principal principal, @PathVariable String userId ) {
		Mono<User> user = 
				userService.getUserByScreenName(principal.getName());
		user.subscribe(u ->{
							if(!u.getUserId().equalsIgnoreCase(userId)) {
								u.getFollowing().add(userId);
								userService.save(u);
							}
		});
	}
}
