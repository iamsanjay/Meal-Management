package com.uvic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="food_entry")
public class FoodEntry {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="food_id")
    private Long foodId;

    public FoodEntry(Long foodId, MealEntry mealEntry, Date date, String userId) {
        this.foodId = foodId;
        this.mealEntry = mealEntry;
        this.date = date;
        this.userId = userId;
    }

    @ManyToOne//(cascade = {CascadeType.MERGE, CascadeType.ALL})
    @JoinColumn(name = "meal_id")
    private MealEntry mealEntry;

    @Column(name = "fdate")
    private Date date;

    @Column(name="user_id")
    private String userId;



    public FoodEntry() { }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }



    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "FoodEntry{" +
                "foodId=" + foodId +
                ", mealEntry=" + mealEntry +
                ", date=" + date +
                ", userId='" + userId + '\'' +
                '}';
    }

    public MealEntry getMealEntry() {
        return mealEntry;
    }

    public void setMealEntry(MealEntry mealEntry) {
        this.mealEntry = mealEntry;
    }
}
