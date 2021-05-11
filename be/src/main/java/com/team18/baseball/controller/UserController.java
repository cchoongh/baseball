package com.team18.baseball.controller;

import com.team18.baseball.entity.User;
import com.team18.baseball.repository.UserRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users/{id}/login")
    public String login(@PathVariable Long id, HttpSession session) {
        User user =userRepository.findById(id).orElseThrow(IllegalStateException::new);
        session.setAttribute("sessionedUser", user);
        return "Login Success!";
    }

    @PostMapping("/users/{id}/logout")
    public String logout(@PathVariable Long id, HttpSession session) {
        User user =userRepository.findById(id).orElseThrow(IllegalStateException::new);
        session.removeAttribute("sessionedUser");
        return "Logout Success!";
    }

}