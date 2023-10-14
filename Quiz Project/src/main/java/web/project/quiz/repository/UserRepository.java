package web.project.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.project.quiz.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
