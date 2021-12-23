package engine.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompletedQuiz {
    @Id
    @GeneratedValue
    Integer id;
    @Getter
    LocalDateTime completedAt;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    Quiz quiz;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    public User getUser() {
        return user;
    }

    public Quiz getQuiz() {
        return quiz;
    }
}
