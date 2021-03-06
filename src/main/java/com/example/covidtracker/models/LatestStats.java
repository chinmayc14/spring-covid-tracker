package com.example.covidtracker.models;

public class LatestStats {
    private String state;
    private String country;
    private int latestTotalCases;
    private int diffFromPrevDay;
    public LatestStats() {
    }

    public LatestStats(String state, String country, int latestTotalCases, int diffFromPrevDay) {
        this.state = state;
        this.country = country;
        this.latestTotalCases = latestTotalCases;
        this.diffFromPrevDay = diffFromPrevDay;
    }



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    public int getDiffFromPrevDay() {
        return diffFromPrevDay;
    }

    public void setDiffFromPrevDay(int diffFromPrevDay) {
        this.diffFromPrevDay = diffFromPrevDay;
    }
}
