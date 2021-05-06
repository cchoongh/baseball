package com.team18.baseball.domain;

public class PlateAppearanceInfo {
    private final Long id;
    private final Long batterId;
    private final BattingResult battingResult;
    private final boolean isOut;
    private final int inningIndex;
    private final InningType inningType;

    PlateAppearanceInfo(Long id,
                        Long batterId, BattingResult battingResult, boolean isOut,
                        int inningIndex, InningType inningType) {
        this.id = id;
        this.batterId = batterId;
        this.battingResult = battingResult;
        this.isOut = isOut;
        this.inningIndex = inningIndex;
        this.inningType = inningType;
    }

    public static PlateAppearanceInfo create(Long id,
                                             Long batterId, BattingResult battingResult,
                                             boolean isOut, int inningIndex, InningType inningType) {
        return new PlateAppearanceInfo(id,
                batterId, battingResult, isOut, inningIndex, inningType);
    }
}
