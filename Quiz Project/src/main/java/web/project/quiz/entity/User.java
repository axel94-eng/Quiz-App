package web.project.quiz.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String userName;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserQuizHistory> userQuizHistory;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<UserQuizHistory> getUserQuizHistory() {
        return userQuizHistory;
    }

    public void setUserQuizHistory(List<UserQuizHistory> userQuizHistory) {
        this.userQuizHistory = userQuizHistory;
    }
}
