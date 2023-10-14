package com.project;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.project.quiz.entity.Role;
import web.project.quiz.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserTest {

    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Test
    public void testUser() {
        try {
            // Create a User instance
            User user = getUser();
            assert user.getUserName().equals("user1");
            assert user.getEmail().equals("user1@example.com");
            assert user.getPassword().equals("password123");
            assert user.getRoles().size() == 1;

        } catch (Exception e) {
            // Log any exceptions that occur during the test
            logger.error("An error occurred in the test: " + e.getMessage(), e);
        }
    }

    private static User getUser() {
        User user = new User();

        // Set some values
        user.setId(1L);
        user.setUserName("user1");
        user.setEmail("user1@example.com");
        user.setPassword("password123");

        // Create a list of roles and add them to the user
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        roles.add(role);

        user.setRoles(roles);

        // Verify the values
        assert user.getId() == 1L;
        return user;
    }
}