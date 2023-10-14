package web.project.quiz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import web.project.quiz.entity.QuizDomain;
import web.project.quiz.repository.DomainRepository;
import web.project.quiz.repository.UserRepository;
import web.project.quiz.service.QuizDomainService;
@Service
public class QuizDomainServiceImpl implements QuizDomainService {
    private static Logger logger = LoggerFactory.getLogger(QuizDomainServiceImpl.class);
    private DomainRepository domainRepository;

    public QuizDomainServiceImpl(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @Override
    public QuizDomain getDomainById(int domainId) throws Exception {
        QuizDomain domain = new QuizDomain();
        try {
            domain = domainRepository.findById(domainId).orElse(null);
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            throw new Exception(exception.getMessage(), exception);
        }
        return domain;
    }
}
