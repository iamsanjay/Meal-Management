package com.uvic.service;

import com.uvic.model.FoodEntriesStat;
import com.uvic.repo.FoodEntryDAO;
import com.uvic.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    FoodEntryDAO foodEntryDAO;

    @Override
    public Map<String, Double> fetchAvgCaloriesPerUserForLastSevenDays() {
        Date endDate = new Date();
        Date startDate = getNDaysAgoDate(6);
        Map<String, Double> data = foodEntryDAO.findFoodEntriesByDateBetween(startDate, endDate)
                .stream()
                .collect(Collectors.groupingBy(
                        foodEntry ->
                                (String) foodEntry.getUserId(), Collectors.summingDouble(e -> e.getMealEntry().getCalories())

                ));

        Set<String> keys = data.keySet();
        for(String k: keys){
            data.compute(k, (key,val) -> Utility.formatDecimalToPrecision2(val/7));
        }
        return data;
    }

    @Override
    public FoodEntriesStat fetchFoodEntriesStat() {
        Date todayDate = getNDaysAgoDate(0);
        int dayEntriesCount = foodEntryDAO.countAllByDate(todayDate);
        Date sevenDaysAgo = getNDaysAgoDate(6);
        int sevenDaysEntriesCount = foodEntryDAO.countAllByDateBetween(sevenDaysAgo, todayDate);
        Date aWeekBeforeSevenDaysEnd = getNDaysAgoDate(7);
        Date aWeekBeforeSevenDaysStart = getNDaysAgoDate(13);
        int aWeekBeforeEntriesCount = foodEntryDAO.countAllByDateBetween(aWeekBeforeSevenDaysStart, aWeekBeforeSevenDaysEnd);
        return new FoodEntriesStat(dayEntriesCount, sevenDaysEntriesCount, aWeekBeforeEntriesCount);
    }

    private Date getNDaysAgoDate(int days){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now().minusDays(days);
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        return date;
    }
}
