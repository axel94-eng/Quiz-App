package web.project.quiz.dto;

import java.util.List;

public class QuizResponseDto {
    private List<QuestionResponseDto> questions;
    private int score;

    public List<QuestionResponseDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionResponseDto> questions) {
        this.questions = questions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
