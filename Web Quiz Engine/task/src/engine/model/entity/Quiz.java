package engine.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Quiz {
    @Id
    @GeneratedValue
    int id;
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

    @JsonIgnore
    public List<Integer> getAnswer() {
        return answer;
    }

    @JsonSetter
    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
