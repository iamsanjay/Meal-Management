package com.uvic.repo;

import com.uvic.model.FoodEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface FoodEntryDAO extends JpaRepository<FoodEntry, Long> {

    List<FoodEntry> findFoodEntriesByUserId(String user_id);
    FoodEntry findFoodEntriesByFoodId(Long foodId);
    List<FoodEntry> findFoodEntriesByUserIdAndDate(String user_id, Date date);
    List<FoodEntry> findFoodEntriesByDateBetween(Date startDate, Date endDate);
    int countAllByDate(Date date);
    int countAllByDateBetween(Date startDate, Date endDate);
}
