package com.uvic.controller;

import com.uvic.config.SCAConfig;
import com.uvic.model.FoodEntriesStat;
import com.uvic.service.AdminService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
@Import(AdminController.class)
@ContextConfiguration(classes = {SCAConfig.class})
@ActiveProfiles("test")
public class AdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AdminService adminService;


    @BeforeEach
    public void test_SetUp(){
        Map<String, Double> avgCaloriesPerUserLastSevenDays = new HashMap<>();
        avgCaloriesPerUserLastSevenDays.put("alice", 200d);
        avgCaloriesPerUserLastSevenDays.put("bob", 210d);
        when(adminService.fetchAvgCaloriesPerUserForLastSevenDays()).thenReturn(avgCaloriesPerUserLastSevenDays);
        FoodEntriesStat foodEntriesStat = new FoodEntriesStat(1, 10, 100);
        when(adminService.fetchFoodEntriesStat()).thenReturn(foodEntriesStat);
    }

    @Test
    public void test_getAvgCaloriesConsumptionPerUserSevenDays() throws Exception {
        mockMvc.perform(get("/admin/report1/")
            .header("user","admin")
            .header("access_token", "1234")
        ).andExpect(status().isOk())
        .andExpect(content().string(Matchers.containsString("alice")));
    }
    @Test
    public void test_getFoodEntriesStat() throws Exception {
        mockMvc.perform(get("/admin/report2/")
                .header("user","admin")
                .header("access_token", "1234")
        ).andExpect(status().isOk())
        .andExpect(content().string(Matchers.containsString("100")));
    }
}
