package com.project;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.project.quiz.entity.Question;

public class QuestionTest {

    private static final Logger logger = LoggerFactory.getLogger(QuestionTest.class);

    @Test
    public void testQuestion() {
        try {
            // Create a Question instance
            Question question = getQuestion();
            assert question.getOptionOne().equals("Paris");
            assert question.getOptionTwo().equals("Berlin");
            assert question.getOptionThree().equals("Madrid");
            assert question.getOptionFour().equals("Rome");
            assert question.getAnswer() == 1;

        } catch (Exception e) {
            // Log any exceptions that occur during the test
            logger.error("An error occurred in the test: " + e.getMessage(), e);
        }
    }

    private static Question getQuestion() {
        Question question = new Question();

        // Set some values
        question.setId(1L);
        question.setQuizId(2L);
        question.setDescription("What is the capital of France?");
        question.setOptionOne("Paris");
        question.setOptionTwo("Berlin");
        question.setOptionThree("Madrid");
        question.setOptionFour("Rome");
        question.setAnswer(1);

        // Verify the values
        assert question.getId() == 1L;
        assert question.getQuizId() == 2L;
        assert question.getDescription().equals("What is the capital of France?");
        return question;
    }
}