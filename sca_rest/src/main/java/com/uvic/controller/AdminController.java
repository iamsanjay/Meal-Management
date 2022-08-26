package com.uvic.controller;

import com.uvic.model.FoodEntriesStat;
import com.uvic.model.UserDetails;
import com.uvic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    @Resource
    Map<String, String> userTokenMap;
    
    @Autowired
    AdminService adminService;

    @GetMapping(value = "/admin/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDetails> getUsers(){
        List<UserDetails> userDetails = new ArrayList<>();
        userTokenMap
            .forEach((s, s2) -> {
                if(!s.equals("admin"))
                    userDetails.add(new UserDetails(s, s2));
            });
        return userDetails;
    }

    @GetMapping("/admin/report1")
    public Map<String, Double> getAvgCaloriesConsumptionPerUserSevenDays(){
        return adminService.fetchAvgCaloriesPerUserForLastSevenDays();
    }

    @GetMapping("/admin/report2")
    public FoodEntriesStat getFoodEntriesStat(){
        return adminService.fetchFoodEntriesStat();
    }

}
