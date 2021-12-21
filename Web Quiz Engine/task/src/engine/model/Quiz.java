package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    int id;
    @NotBlank
    String title;
    @NotBlank
    String text;
    @NotNull
    @Size(min = 2)
    List<String> options;
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
