package engine.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Quiz {
    @Id
    @GeneratedValue
    Integer id;
    @NotBlank
    String title;
    @NotBlank
    String text;
    @NotNull
    @Size(min = 2)
    @ElementCollection
    List<String> options;
    @ElementCollection
    List<Integer> answer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
    @JsonIgnore
    LocalDateTime completedAt;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "quiz",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    private Set<CompletedQuiz> completedQuizzes = Set.of();

    @JsonIgnore
    public List<Integer> getAnswer() {
        return answer;
    }

    @JsonSetter
    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
