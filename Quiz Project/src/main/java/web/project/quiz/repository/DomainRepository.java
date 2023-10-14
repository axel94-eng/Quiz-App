package web.project.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.project.quiz.entity.QuizDomain;

public interface DomainRepository extends JpaRepository<QuizDomain, Integer> {
}
