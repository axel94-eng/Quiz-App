package web.project.quiz.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "UserQuizHistory")
public class UserQuizHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "quiz_id")
    private long quizId;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "domain_id")
    private int domainId;
    @Column(name = "score")
    private int score;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="quiz_id", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    private Quiz quiz;
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    private User user;
    @ManyToOne
    @JoinColumn(name="domain_id", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    private QuizDomain quizDomain;

    public UserQuizHistory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
}
