package com.hany1.practice.service.team;

import com.hany1.practice.dto.team.GetTeamsResponse;
import com.hany1.practice.entity.team.Team;
import com.hany1.practice.repository.team.TeamRepository;
import com.hany1.practice.vo.team.TeamAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    @Transactional
    public void addTeam(TeamAddRequest request) {
        Team team = new Team(request.getTeamName(), request.getAdvanceNoticePeriod());
        teamRepository.save(team);
    }


    public List<GetTeamsResponse> getTeams() {
        List<GetTeamsResponse> teams = teamRepository.findAll()
                .stream()
                .map(team -> new GetTeamsResponse(team.getTeamName(), team.getManagerName(), team.countMember()))
                .toList();
        return teams;
    }
}
