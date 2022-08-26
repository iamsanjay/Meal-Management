package com.uvic.model;

public class FoodEntriesStat {
    @Override
    public String toString() {
        return "FoodEntriesStat{" +
                "day=" + day +
                ", lastSevenDays=" + lastSevenDays +
                ", weekBeforeSevenDays=" + weekBeforeSevenDays +
                '}';
    }

    private int day;
    private int lastSevenDays;
    private int weekBeforeSevenDays;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getLastSevenDays() {
        return lastSevenDays;
    }

    public void setLastSevenDays(int lastSevenDays) {
        this.lastSevenDays = lastSevenDays;
    }

    public int getWeekBeforeSevenDays() {
        return weekBeforeSevenDays;
    }

    public void setWeekBeforeSevenDays(int weekBeforeSevenDays) {
        this.weekBeforeSevenDays = weekBeforeSevenDays;
    }

    public FoodEntriesStat(){}

    public FoodEntriesStat(int day, int lastSevenDays, int weekBeforeSevenDays) {
        this.day = day;
        this.lastSevenDays = lastSevenDays;
        this.weekBeforeSevenDays = weekBeforeSevenDays;
    }
}
