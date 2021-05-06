package com.team18.baseball.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController("/teams")
public class TeamsController {

    @GetMapping()
    public String readTeams(HttpSession session) {

        return "";
    }




}
