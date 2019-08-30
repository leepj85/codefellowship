package com.leepj.codefellowship.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String body;
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    Date createdAt;

    @ManyToOne
    ApplicationUser owner;

    public Post(ApplicationUser owner, String body ) {
        this.owner = owner;
        this.body = body;
        this.createdAt = new Date();
    }

    public Post() {}

    public ApplicationUser getOwner() {
        return owner;
    }

    public void setOwner(ApplicationUser owner) {
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public String toString() {
        return this.createdAt + ": " + this.body;
    }
}
