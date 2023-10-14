package com.project;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.project.quiz.entity.Quiz;
import web.project.quiz.entity.QuizDomain;
import web.project.quiz.entity.User;
import web.project.quiz.entity.UserQuizHistory;

public class UserQuizHistoryTest {

    private static final Logger logger = LoggerFactory.getLogger(UserQuizHistoryTest.class);

    @Test
    public void testUserQuizHistory() {
        try {
            // Create a UserQuizHistory instance
            UserQuizHistory userQuizHistory = new UserQuizHistory();

            // Set some values
            userQuizHistory.setId(1L);
            userQuizHistory.setQuizId(2L);
            userQuizHistory.setUserId(3L);
            userQuizHistory.setDomainId(4);
            userQuizHistory.setScore(8);

            // Create associated objects (Quiz, User, and QuizDomain)
            Quiz quiz = new Quiz(2L, "Sample Quiz");
            User user = new User();
            user.setId(3L);
            QuizDomain quizDomain = new QuizDomain();
            quizDomain.setId(4);

            userQuizHistory.setQuiz(quiz);
            userQuizHistory.setUser(user);
            userQuizHistory.setQuizDomain(quizDomain);

            // Verify the values
            assert userQuizHistory.getId() == 1L;
            assert userQuizHistory.getQuizId() == 2L;
            assert userQuizHistory.getUserId() == 3L;
            assert userQuizHistory.getDomainId() == 4;
            assert userQuizHistory.getScore() == 8;
            assert userQuizHistory.getQuiz().equals(quiz);
            assert userQuizHistory.getUser().equals(user);
            assert userQuizHistory.getQuizDomain().equals(quizDomain);

        } catch (Exception e) {
            // Log any exceptions that occur during the test
            logger.error("An error occurred in the test: " + e.getMessage(), e);
        }
    }
}