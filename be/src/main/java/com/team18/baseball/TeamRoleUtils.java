package com.team18.baseball;

import com.team18.baseball.entity.game.TeamRole;
import com.team18.baseball.entity.game.TeamType;

public class TeamRoleUtils {
    public static TeamRole checkTeamRole( TeamType teamType, int halfInningIndex) {
        if((halfInningIndex % 2) == 1 ) {
            System.out.println(teamType == TeamType.HOME);
            if(teamType == TeamType.HOME) {
                return TeamRole.FIELDING;
            }
            return TeamRole.BATTING;
        }
        if(teamType == TeamType.HOME) {
            return TeamRole.BATTING;
        }
        return TeamRole.FIELDING;
    }
}

