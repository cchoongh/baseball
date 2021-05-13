package com.team18.baseball.utils;

public class TeamRoleUtils {
    public static TeamTurn checkTeamRole(TeamType teamType, int halfInningIndex) {
        if((halfInningIndex % 2) == 1 ) {
            System.out.println(teamType == TeamType.HOME);
            if(teamType == TeamType.HOME) {
                return TeamTurn.FIELDING;
            }
            return TeamTurn.BATTING;
        }
        if(teamType == TeamType.HOME) {
            return TeamTurn.BATTING;
        }
        return TeamTurn.FIELDING;
    }
}
