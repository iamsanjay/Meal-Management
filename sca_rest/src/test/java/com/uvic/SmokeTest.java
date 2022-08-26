package com.uvic;

import static org.assertj.core.api.Assertions.assertThat;

import com.uvic.controller.AdminController;
import com.uvic.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class SmokeTest {
    @Autowired
    private AdminController adminController;

    @Autowired
    private UserController userController;

    @Test
    public void contextLoads(){
        assertThat(adminController).isNotNull();
        assertThat(userController).isNotNull();
    }
}
