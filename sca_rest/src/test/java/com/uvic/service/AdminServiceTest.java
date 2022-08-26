package com.uvic.service;

import com.uvic.model.FoodEntriesStat;
import com.uvic.model.FoodEntry;
import com.uvic.model.MealEntry;
import com.uvic.util.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private  UserService userService;

    @BeforeEach
    public void setUp(){
        Date todayDate = Utility.getNDaysAgoDate(0);
        Date aWeekAgo = Utility.getNDaysAgoDate(6);
        Date aWeekBeforeStart = Utility.getNDaysAgoDate(7);
        Date aWeekBeforeEnd = Utility.getNDaysAgoDate(13);
        Date beforeTwoWeeks = Utility.getNDaysAgoDate(14);


        MealEntry mealEntry = new MealEntry(null, "Salmon", 200d, "Lunch", "john");
        MealEntry mealEntry1 = new MealEntry(null, "Burger", 610d, "Lunch", "alice");
        MealEntry mealEntry2 = new MealEntry(null, "Walnuts", 610d, "Lunch", "bob");
        MealEntry mealEntry3 = new MealEntry(null, "Soda", 200d, "Lunch", "rhy");
        MealEntry mealEntry4 = new MealEntry(null, "Pecans", 640d, "Lunch", "jack");
        List<FoodEntry> foodEntries = Arrays.asList(
                new FoodEntry(null,mealEntry, todayDate, "john"),
                new FoodEntry(null,mealEntry1, aWeekAgo, "alice"),
                new FoodEntry(null,mealEntry2, aWeekBeforeStart, "bob"),
                new FoodEntry(null,mealEntry3, aWeekBeforeEnd, "rhy"),
                new FoodEntry(null,mealEntry4, beforeTwoWeeks, "jack")

        );

        for (FoodEntry foodEntry:foodEntries){
            try {
                userService.createFoodEntry(foodEntry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test_fetchFoodEntriesStat(){
        FoodEntriesStat foodEntriesStat = adminService.fetchFoodEntriesStat();
        assertAll("It Should give one entry for today, 2 for last 7 days, and 2 for a week before that",
                () -> assertEquals(1, foodEntriesStat.getDay()),
                () -> assertEquals(2, foodEntriesStat.getLastSevenDays()),
                () -> assertEquals(2, foodEntriesStat.getWeekBeforeSevenDays())
                );
    }

    @Test
    public void test_fetchAvgCaloriesPerUserForLastSevenDays(){
        Map<String, Double> avgCaloriesStat = adminService.fetchAvgCaloriesPerUserForLastSevenDays();
        assertAll("bob, rhy, and jack will not be there as there entries were added a week ago.",
                () -> assertTrue(avgCaloriesStat.get("bob") == null),
                () -> assertTrue(avgCaloriesStat.get("rhy") == null),
                () -> assertTrue(avgCaloriesStat.get("jack") == null));
    }

    @ParameterizedTest(name = "{index} => foodEntries={0}, expectedAvgCalories={1}")
    @MethodSource("foodEntriesProvider")
    public void test_fetchAvgCaloriesForOneUserForDifferentScenarios(List<FoodEntry> foodEntries, Double expectedAvgCalories) throws Exception {
        for(FoodEntry foodEntry: foodEntries){
            userService.createFoodEntry(foodEntry);
        }
        Map<String, Double> avgCaloriesStat = adminService.fetchAvgCaloriesPerUserForLastSevenDays();
        assertEquals(expectedAvgCalories, avgCaloriesStat.get("alisha"));
    }
    public static Stream<Arguments> foodEntriesProvider(){
        MealEntry mealEntry = new MealEntry(null, "Salmon", 200d, "Lunch", "alisha");
        MealEntry mealEntry1 = new MealEntry(null, "Burger", 610d, "Lunch", "alisha");
        MealEntry mealEntry2 = new MealEntry(null, "Walnuts", 610d, "Lunch", "alisha");
        Date todayDate = Utility.getNDaysAgoDate(0);
        FoodEntry foodEntryFirst = new FoodEntry(null, mealEntry, todayDate, "alisha");
        Date sevenDaysAgo = Utility.getNDaysAgoDate(6);
        FoodEntry foodEntrySecond = new FoodEntry(null, mealEntry1, sevenDaysAgo, "alisha");
        Date eightDaysAgo = Utility.getNDaysAgoDate(7);
        FoodEntry foodEntryThird = new FoodEntry(null, mealEntry2, eightDaysAgo, "alisha");

        return Stream
                .of(
                        Arguments.of(Arrays.asList(foodEntryFirst), Utility.formatDecimalToPrecision2(200.0/7)),
                        Arguments.of(Arrays.asList(foodEntryFirst, foodEntrySecond), Utility.formatDecimalToPrecision2(810.0/7)),
                        Arguments.of(Arrays.asList(foodEntryFirst, foodEntrySecond,foodEntryThird), Utility.formatDecimalToPrecision2(810.0/7))
                );

    }


}
