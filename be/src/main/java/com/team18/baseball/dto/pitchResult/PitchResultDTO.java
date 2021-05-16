package com.team18.baseball.dto.pitchResult;

import org.springframework.data.relational.core.mapping.Embedded;

public class PitchResultDTO {
    private static final Integer MAX_NUMBER_OF_RUNNER = 4;

    private Long homeId;
    private Long awayId;
    private Long battingTeamId;
    private String pitchResult;
    private Runner[] runners = new Runner[MAX_NUMBER_OF_RUNNER];
    @Embedded.Nullable
    private Batter batter;
    private int nthBatter;
    @Embedded.Nullable
    private BallCount ballCount;
    @Embedded.Nullable
    private Score score;

    private PitchResultDTO() {
    }

    private PitchResultDTO(Long homeId, Long awayId, Long battingTeamId, String pitchResult,
                           Runner[] runners,
                           Batter batter, int nthBatter,
                           BallCount ballCount,
                           Score score) {
        this.homeId = homeId;
        this.awayId = awayId;
        this.battingTeamId = battingTeamId;
        this.pitchResult = pitchResult;
        this.runners = runners;
        this.batter = batter;
        this.nthBatter = nthBatter;
        this.ballCount = ballCount;
        this.score = score;
    }

    public static PitchResultDTO from(PitchResult pitchResult) {
        Runner firstPlayer = Runner.create(pitchResult.getFirst_player(), pitchResult.getFirst_mode());
        Runner secondPlayer = Runner.create(pitchResult.getSecond_player(), pitchResult.getSecond_mode());
        Runner thirdPlayer = Runner.create(pitchResult.getThird_player(), pitchResult.getThird_mode());
        Runner fourthPlayer = Runner.create(pitchResult.getFourth_player(), pitchResult.getFourth_mode());
        Runner[] runners = {firstPlayer, secondPlayer, thirdPlayer, fourthPlayer};
        return new PitchResultDTO(pitchResult.getHomeId(), pitchResult.getAwayId(), pitchResult.getBattingTeamId(), pitchResult.getPitchResult(),
                runners,
                pitchResult.getBatter(), pitchResult.getNthBatter(),
                pitchResult.getBallCount(),
                pitchResult.getScore());
    }

    public static final PitchResultDTO createNull() {
        return new PitchResultDTO();
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

    public int getNthBatter() {
        return nthBatter;
    }

    public BallCount getBallCount() {
        return ballCount;
    }

    public Score getScore() {
        return score;
    }
}
