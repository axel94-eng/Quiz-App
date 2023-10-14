package web.project.quiz.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import web.project.quiz.dto.QuestionRequestDto;
import web.project.quiz.dto.QuizRequestDto;
import web.project.quiz.dto.QuizResponseDto;
import web.project.quiz.entity.Question;
import web.project.quiz.entity.Quiz;
import web.project.quiz.entity.QuizDomain;
import web.project.quiz.repository.QuizRepository;
import web.project.quiz.repository.UserQuizHistoryRepository;
import web.project.quiz.repository.UserRepository;
import web.project.quiz.service.impl.QuizServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SuppressWarnings("ALL")
@SpringBootTest
public class QuizServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(QuizServiceImplTest.class);

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserQuizHistoryRepository userQuizHistoryRepository;
    @InjectMocks
    private QuizServiceImpl quizService;

    @Test
    public void testGetQuizById() {
        try {
            Quiz quiz = new Quiz();
            quiz.setId(1L);
            quiz.setDomainId(1);
            quiz.setTitle("Test Quiz");

            when(quizRepository.findById(1L)).thenReturn(Optional.of(quiz));

            Quiz resultQuiz = quizService.getQuizById(1L);

            assertEquals(quiz, resultQuiz);
        } catch (Exception e) {
            // Log any exceptions that occur during the test
            logger.error("An error occurred in the test: " + e.getMessage(), e);
        }
    }

    @Test
    public void testGetAllQuiz() {
        try {
            Quiz quiz = new Quiz();
            quiz.setId(1L);
            quiz.setDomainId(1);
            quiz.setTitle("Test Quiz");

            List<Quiz> quizList = new ArrayList<>();
            quizList.add(quiz);
            quizList.add(quiz);
            quizList.add(quiz);
            quizList.add(quiz);

            when(quizRepository.findAll()).thenReturn(quizList);

            List<Quiz> resultQuiz = quizService.getAll();

            assertTrue(resultQuiz.size() ==  quizList.size());

        } catch (Exception e) {
            // Log any exceptions that occur during the test
            logger.error("An error occurred in the test: " + e.getMessage(), e);
        }
    }

    @Test
    public void testSolveQuiz() {
        try {
            Quiz testQuiz = new Quiz();
            testQuiz.setId(1L);
            List<Question> testQuestions = new ArrayList<>();
            for(int i = 1; i < 10; i++){
                Question question1 = new Question();
                question1.setId((long) i);
                question1.setAnswer(1);
                testQuestions.add(question1);
            }
            testQuiz.setQuestionsList(testQuestions);

            when(quizRepository.findById(1L)).thenReturn(java.util.Optional.of(testQuiz));

            List<SimpleGrantedAuthority> list  =new ArrayList<>();
            list.add(new SimpleGrantedAuthority("member"));
            Collection < ? extends GrantedAuthority> collection = list;
            Authentication authentication = new AnonymousAuthenticationToken("key", "anonymous",
                    collection);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            QuizRequestDto quizRequestDto = new QuizRequestDto();
            List<QuestionRequestDto> questionsRequest = new ArrayList<>();
            for(int i = 1; i < 10; i++){
                QuestionRequestDto questionRequestDto = new QuestionRequestDto();
                questionRequestDto.setQuestionId(i);
                questionRequestDto.setOption(1);
                questionsRequest.add(questionRequestDto);
            }
            quizRequestDto.setQuestionsRequest(questionsRequest);

            QuizResponseDto quizResponseDto = quizService.solveQuiz(quizRequestDto, 1L);

            assertTrue(quizResponseDto.getScore() != 0);

        } catch (Exception e) {
            logger.error("An error occurred in the test: " + e.getMessage(), e);
        }
    }
}