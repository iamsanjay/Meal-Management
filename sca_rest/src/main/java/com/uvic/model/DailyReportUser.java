package com.uvic.model;

import java.util.Date;

public class DailyReportUser {
    private Date date;
    private Double totalCalories;

    public DailyReportUser(Date date, Double totalCalories) {
        this.date = date;
        this.totalCalories = totalCalories;
    }

    public DailyReportUser(){ }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(Double totalCalories) {
        this.totalCalories = totalCalories;
    }
}
