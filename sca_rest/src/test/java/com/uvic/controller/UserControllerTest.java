package com.uvic.controller;

import com.uvic.config.SCAConfig;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@WebMvcTest(UserController.class)
@Import(UserController.class)
@ContextConfiguration(classes = {SCAConfig.class})
@ActiveProfiles("test")
public class UserControllerTest {
}
