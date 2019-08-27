package com.leepj.codefellowship.controllers;

import com.leepj.codefellowship.models.ApplicationUser;
import com.leepj.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;

@Controller
public class ApplicationUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/users")
    public RedirectView createUser(String firstname, String lastname, Date dateofbirth, String bio, String username, String password) {
        ApplicationUser newUser = new ApplicationUser(firstname, lastname, dateofbirth, bio, username,
                // bcrypt handles hashing/salting
                encoder.encode(password));
        applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/myprofile")
    public String getMyProfile(Principal p, Model m){
        ApplicationUser user = (ApplicationUser) applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("user", user);
        return "myprofile";
    }

}

