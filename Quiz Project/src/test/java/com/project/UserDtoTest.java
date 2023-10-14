package com.project;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.project.quiz.dto.UserDto;

public class UserDtoTest {

    private static final Logger logger = LoggerFactory.getLogger(UserDtoTest.class);

    @Test
    public void testUserDto() {
        try {
            // Create a UserDto instance
            UserDto userDto = new UserDto();

            // Set some values
            userDto.setId(1L);
            userDto.setUserName("johndoe");
            userDto.setEmail("johndoe@example.com");
            userDto.setPassword("password123");

            // Verify the values
            assert userDto.getId() == 1L;
            assert userDto.getUserName().equals("johndoe");
            assert userDto.getEmail().equals("johndoe@example.com");
            assert userDto.getPassword().equals("password123");

        } catch (Exception e) {
            // Log any exceptions that occur during the test
            logger.error("An error occurred in the test: " + e.getMessage(), e);
        }
    }
}
