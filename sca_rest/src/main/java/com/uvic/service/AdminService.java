package com.uvic.service;

import com.uvic.model.FoodEntriesStat;

import java.util.Map;

public interface AdminService {
    public Map<String, Double> fetchAvgCaloriesPerUserForLastSevenDays();
    public FoodEntriesStat fetchFoodEntriesStat();
}
