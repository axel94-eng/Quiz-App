package web.project.quiz.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "domain_id")
    private int domainId;
    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<UserQuizHistory> userQuizHistory;
    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<Question> questionsList;
    @ManyToOne
    @JoinColumn(name="domain_id", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    private QuizDomain quizDomain;

    public Quiz() {
    }

    public Quiz(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UserQuizHistory> getUserQuizHistory() {
        return userQuizHistory;
    }

    public void setUserQuizHistory(List<UserQuizHistory> userQuizHistory) {
        this.userQuizHistory = userQuizHistory;
    }

    public List<Question> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Question> questionsList) {
        this.questionsList = questionsList;
    }

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    public QuizDomain getQuizDomain() {
        return quizDomain;
    }

    public void setQuizDomain(QuizDomain quizDomain) {
        this.quizDomain = quizDomain;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
