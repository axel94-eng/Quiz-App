package web.project.quiz.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import web.project.quiz.dto.UserDto;
import web.project.quiz.dto.UserQuizHistoryDto;
import web.project.quiz.entity.User;
import web.project.quiz.entity.UserQuizHistory;
import web.project.quiz.service.QuizService;
import web.project.quiz.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private  static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private QuizService quizService;
    public UserController(UserService userService, QuizService quizService) {
        this.userService = userService;
        this.quizService = quizService;
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String getLogout(Model model) {
        return "home";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        try {
            User existingUser = userService.getByUserName(userDto.getUserName());

            if (existingUser != null) {
                result.rejectValue("userName", null,
                        "There is already an account registered with the same user name");
            }

            if (result.hasErrors()) {
                model.addAttribute("user", userDto);
                return "/register";
            }

            userService.saveUser(userDto);
            return "redirect:/register?success";
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            return "error";
        }
    }

    @GetMapping("/history")
    public String getHistory(Model model) {
        try {
            Map<String, List<UserQuizHistoryDto>> history = userService.getHistory();
            int nrQuizzesSolved = 0;
            for (Map.Entry<String, List<UserQuizHistoryDto>> entry : history.entrySet()) {
                nrQuizzesSolved += entry.getValue().size();
            }
            int nrOfQuizzes = quizService.getAll().size();
            model.addAttribute("nrQuizzesSolved", nrQuizzesSolved);
            model.addAttribute("nrOfQuizzes", nrOfQuizzes);
            model.addAttribute("history", history);
            return "history";
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            return "error";
        }
    }
}
