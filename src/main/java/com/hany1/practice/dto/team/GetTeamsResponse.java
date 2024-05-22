package com.hany1.practice.dto.team;

import lombok.Getter;

import java.util.List;

@Getter
public class GetTeamsResponse {
    private String teamName;
    private List<String> managerNames;
    private int memberCount;

    public GetTeamsResponse(String teamName, List<String> managerNames, int memberCount) {
        this.teamName = teamName;
        this.managerNames = managerNames;
        this.memberCount = memberCount;
    }
}
