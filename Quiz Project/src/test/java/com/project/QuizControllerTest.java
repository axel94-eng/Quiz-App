package com.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import web.project.quiz.controller.QuizController;
import web.project.quiz.dto.QuizRequestDto;
import web.project.quiz.entity.Quiz;
import web.project.quiz.service.QuizDomainService;
import web.project.quiz.service.QuizService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class QuizControllerTest {

    @InjectMocks
    private QuizController quizController;

    @Mock
    private QuizService quizService;
    @Mock
    private QuizDomainService quizDomainService;

    private Model model;

    @BeforeEach
    void setUp() {
        quizService = mock(QuizService.class);
        quizDomainService = mock(QuizDomainService.class);
        mock(Logger.class);
        quizController = new QuizController(quizService, quizDomainService);
        model = mock(Model.class);
        new QuizRequestDto();
    }

    @Test
    void testGetAllQuizzes() throws Exception {
        // Arrange
        int domainId = 1;
        List<Quiz> quizList = new ArrayList<>(); // Mock your quiz data
        /* mock domain */
        when(quizDomainService.getDomainById(domainId)).thenReturn(null);

        // Act
        String result = quizController.getAllQuizzes(model, domainId);

        // Assert
        assertEquals("quiz-list", result);
        verify(model).addAttribute("quizzes", quizList);
    }

    @Test
    void testGetAllQuizzesWithException() throws Exception {
        // Arrange
        int domainId = 1;
        when(quizDomainService.getDomainById(domainId)).thenThrow(new Exception());

        // Act
        String result = quizController.getAllQuizzes(model, domainId);

        // Assert
        assertEquals("error", result);
        Mockito.mock(Logger.class);
    }


    private static class Logger {
    }
}