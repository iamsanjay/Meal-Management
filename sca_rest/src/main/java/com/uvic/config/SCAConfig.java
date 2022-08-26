package com.uvic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SCAConfig {
    @Bean
    public Map<String, String> userTokenMap(){
        Map userTokenMap = new HashMap<String, String>();
        userTokenMap.put("john", "1234");
        userTokenMap.put("alice", "1234");
        userTokenMap.put("bob", "1234");
        userTokenMap.put("jack", "1234");
        userTokenMap.put("rhy", "1234");
        userTokenMap.put("admin", "1234");
        return userTokenMap;
    }

    @Bean
    public Map<String, Integer> userLimit(){
        Map<String,Integer> userLimit = new HashMap<>();
        userLimit.put("Breakfast", 3);
        userLimit.put("Lunch", 3);
        userLimit.put("Dinner", 2);
        return userLimit;
    }
}
