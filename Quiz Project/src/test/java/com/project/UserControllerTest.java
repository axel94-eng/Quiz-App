package com.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.project.quiz.controller.UserController;
import web.project.quiz.dto.UserDto;
import web.project.quiz.dto.UserQuizHistoryDto;
import web.project.quiz.entity.User;
import web.project.quiz.service.QuizService;
import web.project.quiz.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SuppressWarnings("ALL")
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private QuizService quizService;

    @InjectMocks
    private UserController userController;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RedirectAttributes redirectAttributes;

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
        quizService  = Mockito.mock(QuizService.class);
        userController = new UserController(userService, quizService);
    }

    @Test
    void testRegistration_ValidUser() throws Exception {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setUserName("testUser");

        when(userService.getByUserName("testUser")).thenReturn(null);

        // Act
        String result = userController.registration(userDto, bindingResult, model);

        // Assert
        assertEquals("redirect:/register?success", result);
    }

    @Test
    void testRegistration_ExistingUser() throws Exception {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setUserName("testUser");

        // Initialize existingUser
        User existingUser = new User();
        existingUser.setUserName("testUser");

        // Stub the userService.getByUserName method
        when(userService.getByUserName("testUser")).thenReturn(existingUser);

        // Mock the userService.saveUser method with doNothing()
        Mockito.doNothing().when(userService).saveUser(userDto);

        // Act
        String result = userController.registration(userDto, bindingResult, model);

        // Assert
        assertEquals("/register", result);
    }

    @Test
    void testRegistration_FormErrors() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setUserName("testUser");

        when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        String result = userController.registration(userDto, bindingResult, model);

        // Assert
        assertEquals("/register", result);
    }

    @Test
    void testGetHistory() throws Exception {
        // Arrange
        Map<String, List<UserQuizHistoryDto>> history = new HashMap<>();
        history.put("User1", new ArrayList<>());

        when(userService.getHistory()).thenReturn(history);

        // Act
        String result = userController.getHistory(model);

        // Assert
        assertEquals("history", result);
    }
}