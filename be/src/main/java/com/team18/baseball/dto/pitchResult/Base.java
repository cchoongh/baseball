package com.team18.baseball.dto.pitchResult;

public class Base {
    private int firstBasePlayer;
    private int secondBasePlayer;
    private int thirdBasePlayer;

    Base() {
    }

    Base(int firstBasePlayer, int secondBasePlayer, int thirdBasePlayer) {
        this.firstBasePlayer = firstBasePlayer;
        this.secondBasePlayer = secondBasePlayer;
        this.thirdBasePlayer = thirdBasePlayer;
    }

    public static final Base create(int firstBasePlayer, int secondBasePlayer, int thirdBasePlayer) {
        return new Base(firstBasePlayer, secondBasePlayer, thirdBasePlayer);
    }

    public int getFirstBasePlayer() {
        return firstBasePlayer;
    }

    public int getSecondBasePlayer() {
        return secondBasePlayer;
    }

    public int getThirdBasePlayer() {
        return thirdBasePlayer;
    }
}
