package com.leepj.codefellowship.controllers;

import com.leepj.codefellowship.models.ApplicationUser;
import com.leepj.codefellowship.models.ApplicationUserRepository;
import com.leepj.codefellowship.models.Post;
import com.leepj.codefellowship.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PostController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    PostRepository postRepository;

    @GetMapping("/post")
    public String getPostForm() {
        return "post";
    }

    @PostMapping("/post")
    public RedirectView createPost(Principal p, String body) {
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        Post post = new Post(currentUser, body);
        postRepository.save(post);
        return new RedirectView("/myprofile");
    }
}
