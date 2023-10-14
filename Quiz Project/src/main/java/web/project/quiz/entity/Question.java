package web.project.quiz.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "quiz_id")
    private long quizId;
    @Column(name = "description")
    private String description;
    @Column(name = "option_one")
    private String optionOne;
    @Column(name = "option_two")
    private String optionTwo;
    @Column(name = "option_three")
    private String optionThree;
    @Column(name = "option_four")
    private String optionFour;
    @Column(name = "answer")
    private int answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="quiz_id", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    private Quiz quiz;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    public String getOptionFour() {
        return optionFour;
    }

    public void setOptionFour(String optionFour) {
        this.optionFour = optionFour;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
