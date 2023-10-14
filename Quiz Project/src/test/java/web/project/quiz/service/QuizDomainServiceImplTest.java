package web.project.quiz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;
import web.project.quiz.controller.HomeController;
import web.project.quiz.entity.QuizDomain;
import web.project.quiz.repository.DomainRepository;
import web.project.quiz.service.impl.QuizDomainServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuizDomainServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(QuizDomainServiceImplTest.class);

    @Mock
    private DomainRepository domainRepository;
    @InjectMocks
    private QuizDomainServiceImpl quizDomainService;

    @Test
    public void testGetDomainById() {
        try {
            // Prepare a QuizDomain instance for the test
            QuizDomain expectedDomain = new QuizDomain();
            expectedDomain.setId(1);
            expectedDomain.setDomain("Test Domain");

            // Mock the repository to return the expected QuizDomain when findById is called
            when(domainRepository.findById(1)).thenReturn(Optional.of(expectedDomain));

            // Call the service method
            QuizDomain resultDomain = quizDomainService.getDomainById(1);

            // Verify the result
            assertEquals(expectedDomain, resultDomain);

        } catch (Exception e) {
            // Log any exceptions that occur during the test
            logger.error("An error occurred in the test: " + e.getMessage(), e);
        }
    }
}
