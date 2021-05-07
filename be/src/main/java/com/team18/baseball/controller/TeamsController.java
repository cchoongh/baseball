package com.team18.baseball.controller;

import com.team18.baseball.service.TeamService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/teams")
public class TeamsController {
    private final TeamService teamService;

    public TeamsController(TeamService teamService) {
        this.teamService = teamService;
    }

    public void getMatches() {

    }

}
