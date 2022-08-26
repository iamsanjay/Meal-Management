package com.uvic.service;

import com.uvic.exception.FoodEntryConstraintException;
import com.uvic.model.DailyReportUser;
import com.uvic.model.FoodEntry;
import com.uvic.model.MealEntry;
import com.uvic.repo.FoodEntryDAO;
import com.uvic.repo.MealEntryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    FoodEntryDAO foodEntryDAO;

    @Autowired
    MealEntryDAO mealEntryDAO;



    @Resource
    Map<String, Integer> userLimit;

    @Override
    public void createFoodEntry(FoodEntry foodEntry) throws FoodEntryConstraintException, Exception {
        ensureWithinLimits(foodEntry);
        MealEntry mealEntry = mealEntryDAO.save(foodEntry.getMealEntry());
        foodEntry.setMealEntry(mealEntry);
        foodEntryDAO.save(foodEntry);
    }

    @Override
    public void createMealEntry(MealEntry mealEntry) {
        mealEntryDAO.save(mealEntry);
    }

    @Override
    public List<FoodEntry> getFoodEntries(String userId) {
        return foodEntryDAO.findFoodEntriesByUserId(userId);
    }

    @Override
    public List<MealEntry> getMealEntries(String userId) {
        return mealEntryDAO.findMealEntriesByUserIdAndMealTypeIsNotNull(userId);
    }

    @Override
    public List<DailyReportUser> dayWiseUserAnalysis(String userId) {
        List<DailyReportUser> dailyReportUsers = new ArrayList<>();
        foodEntryDAO
                .findFoodEntriesByUserId(userId)
                .stream()
                .collect(Collectors.groupingBy(
                        foodEntry -> foodEntry.getDate(), Collectors.summingDouble(e -> e.getMealEntry().getCalories())
                ))
                .forEach((date, aDouble) -> dailyReportUsers.add(new DailyReportUser(date,aDouble)));
        return dailyReportUsers;
    }

    public void ensureWithinLimits(FoodEntry foodEntry)throws FoodEntryConstraintException {
        MealEntry mealEntry = foodEntry.getMealEntry();
        if(mealEntry.getMealType() == null){
            return;
        }
        String mealType = mealEntry.getMealType();
        String userId = mealEntry.getUserId();
        int mealLimit = userLimit.get(mealType);
        List<FoodEntry> foodEntries = foodEntryDAO.findFoodEntriesByUserIdAndDate(userId, foodEntry.getDate());
        long mealCount = foodEntries
                .stream()
                .filter(entry -> mealType.equals(entry.getMealEntry().getMealType()))
                .count();
        if (mealCount >= mealLimit){
            throw new FoodEntryConstraintException("Already limit exceeded for "+mealType);
        }
    }

}
