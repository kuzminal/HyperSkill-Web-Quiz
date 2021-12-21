package engine.service;

import engine.model.Quiz;
import engine.model.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    public Quiz getQuiz() {
        Quiz quiz = new Quiz();
        List<String> options = new ArrayList<>();
        options.add("Robot");
        options.add("Tea leaf");
        options.add("Cup of coffee");
        options.add("Bug");
        quiz.setOptions(options);
        quiz.setText("What is depicted on the Java logo?");
        quiz.setTitle("The Java Logo");
        return quiz;
    }

    public Result checkAnswer(Optional<Integer> answer) {
        if (answer.isPresent() && answer.get().equals(2)) {
            return new Result(true, "Congratulations, you're right!");
        } else {
            return new Result(false, "Wrong answer! Please, try again.");
        }
    }
}
