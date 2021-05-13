//package com.team18.baseball.entity;
//
//import com.team18.baseball.dto.batterBoard.BatterRecord;
//import org.springframework.data.annotation.Id;
//
//public class BattingRecord {
//    @Id
//    private Long id;
//    private int playerId;
//    private String action;
//    private int strike;
//    private int ball;
//
//    public BattingRecord(int playerId,
//                         String action,
//                         int strike, int ball) {
//        this.id = null;
//        this.playerId = playerId;
//        this.action = action;
//        this.strike = strike;
//        this.ball = ball;
//    }
//
//    public static final BattingRecord from(BatterRecord batterRecord, Long halfInningId) {
//        return new BattingRecord(batterRecord.getPlayerId(),
//                batterRecord.getAction(),
//                batterRecord.getBallCount().getStrike(), batterRecord.getBallCount().getBall());
//    }
//
//
//}
