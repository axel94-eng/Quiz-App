package web.project.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.project.quiz.entity.Quiz;
import web.project.quiz.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
