package com.team18.baseball;

import com.team18.baseball.entity.game.TeamRole;
import com.team18.baseball.entity.game.TeamType;

public class TeamRoleUtils {
    public static final TeamRole checkTeamRole(TeamType teamType, int halfInningIndex) {
        if((halfInningIndex / 2) == 1 ) {
            return TeamRole.FIELDING;
        }
        return TeamRole.BATTING;
    }
}
