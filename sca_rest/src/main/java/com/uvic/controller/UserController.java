package com.uvic.controller;
import com.uvic.exception.FoodEntryConstraintException;
import com.uvic.model.DailyReportUser;
import com.uvic.model.FoodEntry;
import com.uvic.model.MealEntry;
import com.uvic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Resource
    Map<String, String> userTokenMap;



    @GetMapping(value = "/foodentries", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FoodEntry> getFoodEntries(@RequestHeader("user") String userId){
        List<FoodEntry> x = userService.getFoodEntries(userId);
        return x;
    }

    @GetMapping(value = "/mealentries", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealEntry> getMealEntries(@RequestHeader("user") String userId){
        List<MealEntry> x = userService.getMealEntries(userId);
        return x;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/mealentry/create")
    public ResponseEntity<String> createMealEntry(@RequestBody MealEntry mealEntry){
        userService.createMealEntry(mealEntry);
        return new ResponseEntity<>(
                "New Meal Entry added.",
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/foodentry/create")
    public ResponseEntity<String> createFoodEntry(@RequestBody FoodEntry foodEntry){
        try {
            userService.createFoodEntry(foodEntry);
        }
        catch(FoodEntryConstraintException e){
            return new ResponseEntity<>(
                    "User exceeds limit.",
                    HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Something went wrong while adding food entry.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(
               "New food entry added.",
                HttpStatus.OK);
    }

    @GetMapping("/changetoken")
    public ResponseEntity<String> changeAccessToken(@RequestHeader String user, @RequestParam String newToken){
        userTokenMap.put(user, newToken);
        return new ResponseEntity<>("Token Changed", HttpStatus.OK);
    }

    @GetMapping("/daywiseconsumption")
    public List<DailyReportUser> dayWiseCaloriesConsumption(@RequestHeader String user){
        return userService.dayWiseUserAnalysis(user);
    }
}
