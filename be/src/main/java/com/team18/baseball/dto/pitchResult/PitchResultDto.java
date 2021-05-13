package com.team18.baseball.dto.pitchResult;

import com.team18.baseball.entity.Player;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

import java.util.List;

public class PitchResultDto {
    private Long homeId;
    private Long awayId;
    private Long battingTeamId;
    private String pitchResult;
    private Runner[] runners = new Runner[4];
    @Embedded.Nullable
    private Batter batter;
    @Embedded.Nullable
    private BallCount ballCount;
    @Embedded.Nullable
    private Score score;

    PitchResultDto() {
    }

    public PitchResultDto(Long homeId, Long awayId, Long battingTeamId, String pitchResult,
                          Runner[] runners,
                          Batter batter,
                          BallCount ballCount,
                          Score score) {
        this.homeId = homeId;
        this.awayId = awayId;
        this.battingTeamId = battingTeamId;
        this.pitchResult = pitchResult;
        this.runners = runners;
        this.batter = batter;
        this.ballCount = ballCount;
        this.score = score;
    }

    public static final PitchResultDto from(PitchResult pitchResult) {
        Runner firstPlayer = Runner.create(pitchResult.getFirst_player(), pitchResult.getFirst_mode());
        Runner secondPlayer = Runner.create(pitchResult.getSecond_player(), pitchResult.getSecond_mode());
        Runner thirdPlayer = Runner.create(pitchResult.getThird_player(), pitchResult.getThird_mode());
        Runner fourthPlayer = Runner.create(pitchResult.getFourth_player(), pitchResult.getFourth_mode());
        Runner[] runners = {firstPlayer, secondPlayer, thirdPlayer, fourthPlayer};
        return new PitchResultDto(pitchResult.getHomeId(), pitchResult.getAwayId(), pitchResult.getBattingTeamId(), pitchResult.getPitchResult(),
                runners,
                pitchResult.getBatter(),
                pitchResult.getBallCount(),
                pitchResult.getScore());
    }

    public Long getHomeId() {
        return homeId;
    }

    public Long getAwayId() {
        return awayId;
    }

    public Long getBattingTeamId() {
        return battingTeamId;
    }

    public String getPitchResult() {
        return pitchResult;
    }

    public Runner[] getRunners() {
        return runners;
    }

    public Batter getBatter() {
        return batter;
    }

    public BallCount getBallCount() {
        return ballCount;
    }

    public Score getScore() {
        return score;
    }
}
