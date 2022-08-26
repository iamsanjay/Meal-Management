package com.uvic.service;

import com.uvic.exception.FoodEntryConstraintException;
import com.uvic.model.DailyReportUser;
import com.uvic.model.FoodEntry;
import com.uvic.model.MealEntry;

import java.util.List;

public interface UserService {
    public void createFoodEntry(FoodEntry foodEntry) throws FoodEntryConstraintException, Exception;
    public void createMealEntry(MealEntry mealEntry);

    public List<FoodEntry> getFoodEntries(String userId);

    public List<MealEntry> getMealEntries(String userId);

    public List<DailyReportUser> dayWiseUserAnalysis(String userId);
}
