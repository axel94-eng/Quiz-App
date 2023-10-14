//package com.project;
//
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import web.project.quiz.entity.Role;
//import web.project.quiz.entity.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RoleTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(RoleTest.class);
//
//    @Test
//    public void testRole() {
//        try {
//            // Create a Role instance
//            Role role = new Role();
//
//            // Set some values
//            role.setId(1L);
//            role.setName("ROLE_USER");
//
//            // Create a list of users and add them to the role
//            List<User> users = new ArrayList<>();
//            User user1 = new User();
//            user1.setId(1L);
//            user1.setUserName("user1");
//            users.add(user1);
//
//            role.setUsers(users);
//
//            // Verify the values
//            assert role.getId() == 1L;
//            assert role.getName().equals("ROLE_USER");
//            assert role.getUsers().size() == 1;
//
//        } catch (Exception e) {
//            // Log any exceptions that occur during the test
//            logger.error("An error occurred in the test: " + e.getMessage(), e);
//        }
//    }
//}