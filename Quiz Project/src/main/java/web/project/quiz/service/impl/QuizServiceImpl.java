package web.project.quiz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import web.project.quiz.dto.QuestionRequestDto;
import web.project.quiz.dto.QuestionResponseDto;
import web.project.quiz.dto.QuizRequestDto;
import web.project.quiz.dto.QuizResponseDto;
import web.project.quiz.entity.Question;
import web.project.quiz.entity.Quiz;
import web.project.quiz.entity.User;
import web.project.quiz.entity.UserQuizHistory;
import web.project.quiz.repository.QuizRepository;
import web.project.quiz.repository.UserQuizHistoryRepository;
import web.project.quiz.repository.UserRepository;
import web.project.quiz.service.QuizService;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuizServiceImpl implements QuizService {

    private  static Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);

    private QuizRepository quizRepository;
    private UserRepository userRepository;
    private UserQuizHistoryRepository userQuizHistoryRepository;

    public QuizServiceImpl(QuizRepository quizRepository, UserRepository userRepository, UserQuizHistoryRepository userQuizHistoryRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.userQuizHistoryRepository = userQuizHistoryRepository;
    }

    @Override
    public List<Quiz> getAll() throws Exception {
        List<Quiz> quizzes = new ArrayList<>();
        try {
            quizzes = quizRepository.findAll();
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            throw new Exception(exception.getMessage(), exception);
        }
        return quizzes;
    }

    @Override
    public Quiz getQuizById(Long userId) throws Exception {
        Quiz quiz = new Quiz();
        try {
            quiz = quizRepository.findById(userId).orElse(null);
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            throw new Exception(exception.getMessage(), exception);
        }
        return quiz;
    }
    @Override
    public QuizResponseDto solveQuiz(QuizRequestDto quizRequestDto, Long quizId) throws Exception {
        User user = null;
        List<QuestionResponseDto> questionList = new ArrayList<>();
        QuizResponseDto response = new QuizResponseDto();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
                System.out.println("User: " + currentUserName);
                user = userRepository.findByUserName(currentUserName);
            }

            int score = 10;
            Quiz quiz = getQuizById(quizId);
            List<QuestionRequestDto> userAnswers = quizRequestDto.getQuestionsRequest();
            for (Question question : quiz.getQuestionsList()) {
                System.out.println("step 2 question id " + question.getId());
                QuestionRequestDto questionRequestDto = userAnswers
                        .stream()
                        .filter(p -> p.getQuestionId() == question.getId())
                        .findFirst().orElse(null);
                if (questionRequestDto != null ) {
                    QuestionResponseDto questionResponseDto = mapToDto(question, questionRequestDto.getOption());
                    System.out.println("Q nr " + questionResponseDto.getId() + " - " + questionResponseDto.getUserOption());
                    questionList.add(questionResponseDto);
                }
                if (questionRequestDto != null && question.getAnswer() != questionRequestDto.getOption()){
                    score -= 1;
                }
            }
            response.setQuestions(questionList);
            response.setScore(score);

            UserQuizHistory userQuizHistory = userQuizHistoryRepository.findByUserIdAndQuizId(user.getId(), quizId);
            if(userQuizHistory == null){
                userQuizHistory = new UserQuizHistory();
                userQuizHistory.setQuizId(quizId);
                userQuizHistory.setUserId(user.getId());
                userQuizHistory.setDomainId(quiz.getDomainId());
                userQuizHistory.setScore(score);
            } else {
                userQuizHistory.setScore(score);
            }
            userQuizHistoryRepository.save(userQuizHistory);
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            throw new Exception(exception.getMessage(), exception);
        }
        return response;
    }

    private QuestionResponseDto mapToDto(Question question, int userAnswer){
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setId(question.getId());
        questionResponseDto.setDescription(question.getDescription());
        questionResponseDto.setOptionOne(question.getOptionOne());
        questionResponseDto.setOptionTwo(question.getOptionTwo());
        questionResponseDto.setOptionThree(question.getOptionThree());
        questionResponseDto.setOptionFour(question.getOptionFour());
        questionResponseDto.setUserOption(userAnswer);
        questionResponseDto.setAnswer(question.getAnswer());
        return questionResponseDto;
    }
}
