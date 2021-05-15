package com.team18.baseball.dto.pitchResult;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

public class PitchResult {
    @Id
    private Long id;
    private Long homeId;
    private Long awayId;
    private Long battingTeamId;
    private String pitchResult;
    private Long first_player;
    private String first_mode;
    private Long second_player;
    private String second_mode;
    private Long third_player;
    private String third_mode;
    private Long fourth_player;
    private String fourth_mode;
    @Embedded.Nullable
    private Batter batter;
    private int nthBatter;
    @Embedded.Nullable
    private BallCount ballCount;
    @Embedded.Nullable
    private Score score;

    public PitchResult() {
        this.homeId = 0L;
        this.awayId = 0L;
        this.battingTeamId = null;
        this.pitchResult = null;
        this.first_player = 0L;
        this.first_mode = null;
        this.second_player = 0L;
        this.second_mode = null;
        this.third_player = 0L;
        this.third_mode = null;
        this.fourth_player = 0L;
        this.fourth_mode = null;
        this.batter = null;
        this.ballCount = null;
        this.score = null;
    }

   public PitchResult(Long homeId, Long awayId, Long battingTeamId, String pitchResult,
                      Batter batter, int nthBatter,
                      BallCount ballCount, Score score) {
       this.id = null;
       this.homeId = homeId;
       this.awayId = awayId;
       this.battingTeamId = battingTeamId;
       this.pitchResult = pitchResult;
       this.first_player = null;
       this.first_mode =  null;
       this.second_player =  null;
       this.second_mode =  null;
       this.third_player =  null;
       this.third_mode =  null;
       this.fourth_player =  null;
       this.fourth_mode =  null;
       this.batter = batter;
       this.nthBatter = nthBatter;
       this.ballCount = ballCount;
       this.score = score;
   }

    public static PitchResult from(PitchResultDto pitchResultDto) {
                return new PitchResult(pitchResultDto.getHomeId(), pitchResultDto.getAwayId(), pitchResultDto.getBattingTeamId(), pitchResultDto.getPitchResult(),
                pitchResultDto.getBatter(), pitchResultDto.getNthBatter(),
                pitchResultDto.getBallCount(),
                pitchResultDto.getScore());
    }

    public void addPlayerInfo(Runner[] runners) {
        if(runners.length >= 1) {
            this.first_player = runners[0].getPlayerId();
            this.first_mode = runners[0].getMode();
        }
        if(runners.length >= 2) {
            this.second_player = runners[1].getPlayerId();
            this.second_mode = runners[1].getMode();
        }
        if(runners.length >= 3) {
            this.third_player = runners[2].getPlayerId();
            this.third_mode = runners[2].getMode();
        }
        if(runners.length == 4) {
            this.fourth_player = runners[3].getPlayerId();
            this.fourth_mode = runners[3].getMode();
        }
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

    public Long getFirst_player() {
        return first_player;
    }

    public String getFirst_mode() {
        return first_mode;
    }

    public Long getSecond_player() {
        return second_player;
    }

    public String getSecond_mode() {
        return second_mode;
    }

    public Long getThird_player() {
        return third_player;
    }

    public String getThird_mode() {
        return third_mode;
    }

    public Long getFourth_player() {
        return fourth_player;
    }

    public String getFourth_mode() {
        return fourth_mode;
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
