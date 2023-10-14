package web.project.quiz.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import web.project.quiz.dto.UserDto;
import web.project.quiz.entity.Quiz;
import web.project.quiz.entity.User;
import web.project.quiz.entity.UserQuizHistory;
import web.project.quiz.repository.UserRepository;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private final UserService userService;

    public UserServiceImplTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testSaveUser() {
        try {
            // Prepare a UserDto for the test
            UserDto testUserDto = new UserDto();
            testUserDto.setUserName("testUser");
            testUserDto.setEmail("testUser@test.com");
            testUserDto.setPassword("password");

            // Mock the passwordEncoder to return a hashed password
            when(passwordEncoder.encode(testUserDto.getPassword())).thenReturn("$2a$12$..."); // Actual hashed password

            // Call the service method to save the user
            userService.saveUser(testUserDto);

            // Mock the repository to return the saved user when findByUserName is called
            when(userRepository.findByUserName(testUserDto.getUserName())).thenReturn(new User());

            // Retrieve the saved user
            User savedUser = userService.getByUserName(testUserDto.getUserName());

            // Verify the saved user
            assertEquals(savedUser.getUserName(), testUserDto.getUserName());
            assertEquals(savedUser.getEmail(), testUserDto.getEmail());
            assertEquals(savedUser.getRoles().size(), 1); // Expecting one role
            assertEquals(savedUser.getRoles().get(0).getName(), "MEMBER");
            // You can add more assertions as needed...

        } catch (Exception e) {
            // Log any exceptions that may occur during the test
            logger.error("An error occurred in the test: " + e.getMessage(), e);
        }
    }

    @Test
    public void testGetHistory() {
        try {
            // Prepare a test user and userQuizHistoryList for the test
            User testUser = new User();
            UserQuizHistory userQuizHistory = new UserQuizHistory();
            userQuizHistory.setScore(8);
            userQuizHistory.setDomainId(1); // Example domain ID
            userQuizHistory.setQuiz(new Quiz());
            userQuizHistory.getQuiz().setTitle("Sample Quiz");
            testUser.setUserQuizHistory(Collections.singletonList(userQuizHistory));

            // Mock the SecurityContextHolder to simulate user authentication
            Authentication authentication = new AnonymousAuthenticationToken("key", "anonymous", null);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Mock the repository to return the test user when findByUserName is called
            when(userRepository.findByUserName("anonymous")).thenReturn(testUser);

            // Call the service method to get user history
            userService.getHistory();

            // Verify the result or add your own assertions...

        } catch (Exception e) {
            // Log any exceptions that may occur during the test
            logger.error("An error occurred in the test: " + e.getMessage(), e);
        }
    }
}