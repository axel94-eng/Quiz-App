package web.project.quiz.service;

import web.project.quiz.entity.Quiz;
import web.project.quiz.entity.QuizDomain;

public interface QuizDomainService {

    public QuizDomain getDomainById(int domainId) throws Exception;

}
