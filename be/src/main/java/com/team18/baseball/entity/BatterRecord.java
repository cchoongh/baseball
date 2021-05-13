package com.team18.baseball.entity;

import com.team18.baseball.dto.batterBoard.BatterRecordDto;
import org.springframework.data.annotation.Id;

import java.util.List;

public class BatterRecord {
    @Id
    private Long id;
    private Long halfInningId;
    private int playerId;
    private String action;
    private int strike;
    private int ball;

    public BatterRecord(Long halfInningId,
                        int playerId,
                        String action,
                        int strike, int ball) {
        this.id = null;
        this.halfInningId = halfInningId;
        this.playerId = playerId;
        this.action = action;
        this.strike = strike;
        this.ball = ball;
    }

    public static final BatterRecord from(BatterRecordDto batterRecordDto, Long halfInningId) {
        return new BatterRecord(halfInningId,
                batterRecordDto.getPlayerId(),
                batterRecordDto.getAction(),
                batterRecordDto.getBallCount().getStrike(), batterRecordDto.getBallCount().getBall());
    }


}
