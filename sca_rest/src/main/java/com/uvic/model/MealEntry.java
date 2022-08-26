package com.uvic.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "meal_entry")
public class MealEntry {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long mealId;

    @Column(name = "meal_name")
    private String name;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "meal_type")
    private String mealType;

    @Column(name = "user_id")
    private String userId;

    public MealEntry(Long mealId, String name, Double calories, String mealType, String userId) {
        this.mealId = mealId;
        this.name = name;
        this.calories = calories;
        this.mealType = mealType;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public MealEntry(){}

    @Override
    public String toString() {
        return "MealEntry{" +
                "mealID=" + mealId +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", mealType='" + mealType + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
