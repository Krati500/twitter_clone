package com.tweety.tweety_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class User {
    @Id
    @NotNull
    private String userId;

    @JsonIgnore
    @NotNull
    private String password;

    @NotNull
    @Column(unique = true)
    private String screenName;

    @NotNull
    private RoleEnum role;

    private String bio;

    private String profileImage;

    @ElementCollection
    private Set<String> following;
}
