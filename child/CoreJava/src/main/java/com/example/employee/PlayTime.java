package com.example.employee;

public class PlayTime {

    String playerName;
    String gameName;
    Integer playTime;
    String teamName;
    String gameType;
    Double gamePrice;


    public PlayTime(String playerName, String gameName, Integer playTime, String teamName, String gameType, Double gamePrice) {
        this.playerName = playerName;
        this.gameName = gameName;
        this.playTime = playTime;
        this.teamName = teamName;
        this.gameType = gameType;
        this.gamePrice = gamePrice;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Integer playTime) {
        this.playTime = playTime;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public Double getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(Double gamePrice) {
        this.gamePrice = gamePrice;
    }
}