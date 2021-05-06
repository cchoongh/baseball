package com.team18.baseball.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @PostMapping("/users/{id}")
    public String login(@PathVariable Long id, HttpSession session) {
        session.setAttribute("user", id);
        return "Success!";
    }

}
