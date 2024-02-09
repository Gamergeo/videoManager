package com.gamergeo.project.videomanager.gui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

//@run(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
		  locations = "classpath:application-test.properties")
class VideoManagerApiTests {
	
    @Test
    void contextLoads() {
    	assertTrue(true);
    }
}