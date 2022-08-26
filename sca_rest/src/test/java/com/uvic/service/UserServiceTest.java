package com.uvic.service;

import com.uvic.model.FoodEntry;
import com.uvic.model.MealEntry;
import com.uvic.repo.FoodEntryDAO;
import com.uvic.util.Utility;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    FoodEntryDAO foodEntryDAO;

    @Autowired
    UserService userService;

    @ParameterizedTest
    @MethodSource("foodEntriesProvider")
    public void test_ensureLimitMethod_atDifferentFoodEntries(List<FoodEntry> foodEntries) throws Exception{
        assertDoesNotThrow(
                () -> {
                    for (FoodEntry foodEntry: foodEntries){
                        userService.createFoodEntry(foodEntry);
                    }
                }
        );
    }
    public static Stream<List<FoodEntry>> foodEntriesProvider(){
        MealEntry mealEntry = new MealEntry(null, "Salmon", 200d, "Breakfast", "alisha");
        MealEntry mealEntry1 = new MealEntry(null, "Burger", 610d, "Lunch", "alisha");
        MealEntry mealEntry2 = new MealEntry(null, "Walnuts", 610d, "Dinner", "alisha");

        Date todayDate = Utility.getNDaysAgoDate(0);
        FoodEntry firstBreakfast = new FoodEntry(null, mealEntry, todayDate, "alisha");
        Date sevenDaysAgo = Utility.getNDaysAgoDate(6);
        FoodEntry secondBreakfast = new FoodEntry(null, mealEntry, todayDate, "alisha");
        Date eightDaysAgo = Utility.getNDaysAgoDate(7);
        FoodEntry thirdBreakfast = new FoodEntry(null, mealEntry, todayDate, "alisha");


        FoodEntry firstLunch = new FoodEntry(null, mealEntry1, todayDate, "alisha");
        FoodEntry secondLunch = new FoodEntry(null, mealEntry1, todayDate, "alisha");

        FoodEntry firstDiner = new FoodEntry(null, mealEntry2, todayDate, "alisha");

        return Stream
                .of(
                        Arrays.asList(firstDiner),
                        Arrays.asList(firstLunch, secondLunch),
                        Arrays.asList(firstBreakfast, secondBreakfast,thirdBreakfast)
                );

    }


}
