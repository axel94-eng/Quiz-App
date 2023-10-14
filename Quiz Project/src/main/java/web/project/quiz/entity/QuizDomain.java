package web.project.quiz.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "QuizDomain")
public class QuizDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "domain")
    private String domain;

    @OneToMany(mappedBy = "quizDomain", fetch = FetchType.LAZY)
    private List<Quiz> quizList;

    @OneToMany(mappedBy = "quizDomain", fetch = FetchType.LAZY)
    private List<UserQuizHistory> userQuizHistory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    public List<UserQuizHistory> getUserQuizHistory() {
        return userQuizHistory;
    }

    public void setUserQuizHistory(List<UserQuizHistory> userQuizHistory) {
        this.userQuizHistory = userQuizHistory;
    }
}
