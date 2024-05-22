package com.hany1.practice.controller.team;

import com.hany1.practice.dto.team.GetTeamsResponse;
import com.hany1.practice.service.team.TeamService;
import com.hany1.practice.vo.team.TeamAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @PostMapping("/team")
    public void addTeam(@RequestBody TeamAddRequest request) {
        teamService.addTeam(request);
    }

    @GetMapping("/team/all")
    public List<GetTeamsResponse> getTeams() {
        return teamService.getTeams();
    }
}
