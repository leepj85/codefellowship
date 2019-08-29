package com.leepj.codefellowship.controllers;

import com.leepj.codefellowship.models.ApplicationUser;
import com.leepj.codefellowship.models.ApplicationUserRepository;
import com.leepj.codefellowship.models.Post;
import com.leepj.codefellowship.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class PostController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    PostRepository postRepository;

    @GetMapping("/post")
    public String getPostForm(Principal p, Model m) {
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("user", currentUser);
        return "post";
    }

    @PostMapping("/post")
    public RedirectView createPost(Principal p, String body) {
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());

        Post post = new Post(currentUser, body);
        postRepository.save(post);
        return new RedirectView("/myprofile");
    }

    @GetMapping("/feed")
    public String getFeed(Principal p, Model m){
        Set<ApplicationUser> followedUsers = applicationUserRepository.findByUsername(p.getName()).getFollowing();
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        List<Post> myFeed = new ArrayList<Post>();
        for (ApplicationUser user: followedUsers){
            for(Post post: user.getPosts()){
                myFeed.add(post);
            }
        }
        m.addAttribute("followedUsers", followedUsers);
        m.addAttribute("myFeed", myFeed);
        m.addAttribute("user", currentUser);
        return "feed";
    }

    @PostMapping("/feed")
    public RedirectView createPost(long follower, long followee){
        ApplicationUser user = applicationUserRepository.findById(follower).get();
        ApplicationUser userToFollow = applicationUserRepository.findById(followee).get();
        user.addFollow(userToFollow);
        applicationUserRepository.save(user);
        return new RedirectView("/feed");
    }
}
