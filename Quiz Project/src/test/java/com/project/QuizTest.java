package com.project;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.project.quiz.entity.Quiz;

public class QuizTest {

    private static final Logger logger = LoggerFactory.getLogger(QuizTest.class);

    @Test
    public void testQuiz() {
        try {
            // Create a Quiz instance
            Quiz quiz = new Quiz();

            // Set some values
            quiz.setId(1L);
            quiz.setTitle("General Knowledge Quiz");
            quiz.setDomainId(2);

            // Verify the values
            assert quiz.getId() == 1L;
            assert quiz.getTitle().equals("General Knowledge Quiz");
            assert quiz.getDomainId() == 2;

        } catch (Exception e) {
            // Log any exceptions that occur during the test
            logger.error("An error occurred in the test: " + e.getMessage(), e);
        }
    }
}