package web.project.quiz.dto;

import java.util.List;

public class QuizRequestDto {

    private List<QuestionRequestDto> questionsRequest;

    public List<QuestionRequestDto> getQuestionsRequest() {
        return questionsRequest;
    }

    public void setQuestionsRequest(List<QuestionRequestDto> questionsRequest) {
        this.questionsRequest = questionsRequest;
    }
}
