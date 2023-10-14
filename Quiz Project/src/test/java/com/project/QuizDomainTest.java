package com.project;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.project.quiz.entity.QuizDomain;

public class QuizDomainTest {

    private static final Logger logger = LoggerFactory.getLogger(QuizDomainTest.class);

    @Test
    public void testQuizDomain() {
        try {
            // Create a QuizDomain instance
            QuizDomain quizDomain = new QuizDomain();

            // Set some values
            quizDomain.setId(1);
            quizDomain.setDomain("History");

            // Verify the values
            assert quizDomain.getId() == 1;
            assert quizDomain.getDomain().equals("History");

        } catch (Exception e) {
            // Log any exceptions that occur during the test
            logger.error("An error occurred in the test: " + e.getMessage(), e);
        }
    }
}
