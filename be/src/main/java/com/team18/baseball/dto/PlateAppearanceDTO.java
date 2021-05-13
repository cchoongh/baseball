package com.team18.baseball.dto;
public class PlateAppearanceDTO {

    private PlateAppearanceInfoDTO away;
    private PlateAppearanceInfoDTO home;

    private PlateAppearanceDTO(PlateAppearanceInfoDTO away, PlateAppearanceInfoDTO home) {
        this.away = away;
        this.home = home;
    }

    public static PlateAppearanceDTO create(PlateAppearanceInfoDTO away, PlateAppearanceInfoDTO home) {
        return new PlateAppearanceDTO(away, home);
    }

    public static PlateAppearanceDTO createNull() {
        return new PlateAppearanceDTO(PlateAppearanceInfoDTO.createNull(), PlateAppearanceInfoDTO.createNull());
    }

    public PlateAppearanceInfoDTO getAway() {
        return away;
    }

    public PlateAppearanceInfoDTO getHome() {
        return home;
    }
}
