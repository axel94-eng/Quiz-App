package com.project;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.project.quiz.dto.QuestionResponseDto;

public class QuestionResponseDtoTest {

    private static final Logger logger = LoggerFactory.getLogger(QuestionResponseDtoTest.class);

    @Test
    public void testQuestionResponseDto() {
        try {
            // Create a QuestionResponseDto instance
            QuestionResponseDto questionResponseDto = getQuestionResponseDto();
            assert questionResponseDto.getOptionTwo().equals("Option 2");
            assert questionResponseDto.getOptionThree().equals("Option 3");
            assert questionResponseDto.getOptionFour().equals("Option 4");
            assert questionResponseDto.getAnswer() == 2;
            assert questionResponseDto.getUserOption() == 1;

        } catch (Exception e) {
            // Log any exceptions that occur during the test
            logger.error("An error occurred in the test: " + e.getMessage(), e);
        }
    }

    private static QuestionResponseDto getQuestionResponseDto() {
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();

        // Set some values
        questionResponseDto.setId(1);
        questionResponseDto.setDescription("Sample question");
        questionResponseDto.setOptionOne("Option 1");
        questionResponseDto.setOptionTwo("Option 2");
        questionResponseDto.setOptionThree("Option 3");
        questionResponseDto.setOptionFour("Option 4");
        questionResponseDto.setAnswer(2);
        questionResponseDto.setUserOption(1);

        // Verify the values
        assert questionResponseDto.getId() == 1;
        assert questionResponseDto.getDescription().equals("Sample question");
        assert questionResponseDto.getOptionOne().equals("Option 1");
        return questionResponseDto;
    }
}