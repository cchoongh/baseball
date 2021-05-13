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

    public static PlateAppearanceDTO createNullPA(String awayTeamName, String homeTeamName,
                                                  PlateAppearanceInfoDTO awayPAInfos, PlateAppearanceInfoDTO homePAInfos) {
        return new PlateAppearanceDTO(PlateAppearanceInfoDTO.createNullPAInfo(awayTeamName, awayPAInfos), PlateAppearanceInfoDTO.createNullPAInfo(homeTeamName, homePAInfos));
    }

    public PlateAppearanceInfoDTO getAway() {
        return away;
    }

    public PlateAppearanceInfoDTO getHome() {
        return home;
    }
}
