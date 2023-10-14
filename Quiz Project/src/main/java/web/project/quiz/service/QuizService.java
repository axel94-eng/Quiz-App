package web.project.quiz.service;

import web.project.quiz.dto.QuestionRequestDto;
import web.project.quiz.dto.QuestionResponseDto;
import web.project.quiz.dto.QuizRequestDto;
import web.project.quiz.dto.QuizResponseDto;
import web.project.quiz.entity.Question;
import web.project.quiz.entity.Quiz;

import java.util.ArrayList;
import java.util.List;

public interface QuizService {

    public List<Quiz> getAll() throws Exception;

    public Quiz getQuizById(Long quizId) throws Exception;

    public QuizResponseDto solveQuiz(QuizRequestDto quizRequestDto, Long quizId) throws Exception;

}
