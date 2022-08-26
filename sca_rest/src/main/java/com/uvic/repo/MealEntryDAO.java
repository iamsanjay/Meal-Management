package com.uvic.repo;

import com.uvic.model.FoodEntry;
import com.uvic.model.MealEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealEntryDAO extends JpaRepository<MealEntry, Long> {
    MealEntry findMealEntryByMealId(Long mealId);
    List<MealEntry> findMealEntriesByUserIdAndMealTypeIsNotNull(String userId);

}
