package com.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;
import web.project.quiz.controller.HomeController;
import web.project.quiz.service.QuizDomainService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HomeControllerTest {

    @Mock
    private QuizDomainService quizDomainService;
    @Mock
    private Model model;

    private HomeController homeController;

    @BeforeEach
    void setUp() {
        quizDomainService = mock(QuizDomainService.class);
        model = mock(Model.class);
        homeController = new HomeController(quizDomainService);
    }

    @Test
    void testGetHome() throws Exception {
        // Arrange
        /* your mock response here */
        when(quizDomainService.getDomainById(1)).thenReturn(null);

        // Act
        homeController.getHome(model);


    }

    @Test
    void testGetHomeWithException() {
        // Arrange
        try {
            when(quizDomainService.getDomainById(1)).thenThrow(new Exception());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Act
        homeController.getHome(model);


    }
}