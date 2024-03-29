package com.leepj.codefellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String picURL;
    String firstName;
    String lastName;
    Date dob;
    String bio;
    String username;
    String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    List<Post> posts;

    @ManyToMany
    @JoinTable(
            name = "followers",
            joinColumns = { @JoinColumn(name="follower") },
            inverseJoinColumns = { @JoinColumn(name="followee")}
    )

    Set<ApplicationUser> following;

    @ManyToMany(mappedBy = "following")
    Set<ApplicationUser> followedBy;

    public ApplicationUser(String firstName, String lastName, Date dob, String bio, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.bio = bio;
        this.username = username;
        this.password = password;
    }

    public ApplicationUser(String picURL, String firstName, String lastName, Date dob, String bio, String username, String password) {
        this.picURL = picURL;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.bio = bio;
        this.username = username;
        this.password = password;
    }

    public ApplicationUser() {}


    public void addFollow(ApplicationUser followee){
        following.add(followee);
    }

    public Set<ApplicationUser> getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(Set<ApplicationUser> followedBy) {
        this.followedBy = followedBy;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public Set<ApplicationUser> getFollowing() {
        return following;
    }

    public void setFollowing(Set<ApplicationUser> following) {
        this.following = following;
    }

    public String getPicURL() {
        return this.picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

