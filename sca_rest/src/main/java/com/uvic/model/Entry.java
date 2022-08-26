package com.uvic.model;

public class Entry {
    private MealEntry mealEntry;
    private FoodEntry foodEntry;

    public Entry() { }


    public MealEntry getMealEntry() {
        return mealEntry;
    }

    public void setMealEntry(MealEntry mealEntry) {
        this.mealEntry = mealEntry;
    }

    public FoodEntry getFoodEntry() {
        return foodEntry;
    }

    public void setFoodEntry(FoodEntry foodEntry) {
        this.foodEntry = foodEntry;
    }
}
