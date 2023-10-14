package web.project.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.project.quiz.entity.Quiz;
import web.project.quiz.entity.User;
import web.project.quiz.entity.UserQuizHistory;

@Repository
public interface UserQuizHistoryRepository extends JpaRepository<UserQuizHistory, Long> {
    UserQuizHistory findByUserIdAndQuizId(long userId, long quizId);

}
