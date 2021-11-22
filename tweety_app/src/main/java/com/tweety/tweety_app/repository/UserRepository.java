package com.tweety.tweety_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweety.tweety_app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	User findByScreenName(String screenName);

}
