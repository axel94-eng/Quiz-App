package web.project.quiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.project.quiz.dto.QuestionRequestDto;
import web.project.quiz.dto.QuestionResponseDto;
import web.project.quiz.dto.QuizRequestDto;
import web.project.quiz.dto.QuizResponseDto;
import web.project.quiz.entity.Question;
import web.project.quiz.entity.Quiz;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.project.quiz.service.QuizDomainService;
import web.project.quiz.service.QuizService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuizController {
    private  static Logger logger = LoggerFactory.getLogger(QuizController.class);
    private QuizService quizService;

    private QuizDomainService quizDomainService;

    public QuizController(QuizService quizService, QuizDomainService quizDomainService) {
        this.quizService = quizService;
        this.quizDomainService = quizDomainService;
    }

    @GetMapping("/quizList/{id}")
    public String getAllQuizzes(Model model, @PathVariable int id){
        try {
            List<Quiz> quizList = quizDomainService.getDomainById(id).getQuizList();
            model.addAttribute("quizzes", quizList);
            return "quiz-list";
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            return "error";
        }
    }

    @GetMapping("/quiz/{id}")
    public String getQuizById(Model model, @PathVariable long id){
        try {
            Quiz quiz = quizService.getQuizById(id);
            if (quiz != null) {
                model.addAttribute("domainId", quiz.getDomainId());
                model.addAttribute("quiz", quiz);
                model.addAttribute("questions", quiz.getQuestionsList());
                model.addAttribute("formQuiz", new QuizRequestDto());
            }

            return "quiz";
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            return "error";
        }
    }

    @PostMapping("/submitQuiz/{id}")
    public String solveQuiz(@ModelAttribute("formQuiz") QuizRequestDto quizRequestDto, @PathVariable long id,
                               Model model){
        try {
            QuizResponseDto response = quizService.solveQuiz(quizRequestDto, id);
            model.addAttribute("response", response);

            return "quiz-response";
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            return "error";
        }
    }
}
