package web.project.quiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import web.project.quiz.service.QuizDomainService;
import web.project.quiz.service.QuizService;
import web.project.quiz.service.impl.QuizServiceImpl;

@Controller
public class HomeController {
    private  static Logger logger = LoggerFactory.getLogger(HomeController.class);

    private QuizDomainService quizDomainService;

    public HomeController(QuizDomainService quizDomainService) {
        this.quizDomainService = quizDomainService;
    }
    @GetMapping("/home")
    public String getHome(Model model) {
        try {
            int nrOfGeographyTests = quizDomainService.getDomainById(1).getQuizList().size();
            model.addAttribute("geographyTests", nrOfGeographyTests);
            int nrOfHistoryTests = quizDomainService.getDomainById(1).getQuizList().size();
            model.addAttribute("historyTests", nrOfHistoryTests);
            return "home";
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            return "error";
        }
    }

}
