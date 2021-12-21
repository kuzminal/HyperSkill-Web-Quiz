package engine.service;

import engine.model.Answer;
import engine.model.Quiz;
import engine.model.Result;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class QuizService {
    Map<Integer, Quiz> quizzes = new ConcurrentHashMap<>();

    public Result checkAnswer(int quizId, Answer answer) {
        if (quizzes.get(quizId) != null) {
            if (answer.getAnswer().equals(
                    quizzes.get(quizId).getAnswer() == null ? List.of() : quizzes.get(quizId).getAnswer())
            ) {
                return new Result(true, "Congratulations, you're right!");
            } else {
                return new Result(false, "Wrong answer! Please, try again.");
            }
        } else {
            return null;
        }
    }

    public Quiz saveQuiz(Quiz quiz) {
        quiz.setId(quizzes.size() > 0 ? quizzes.size() + 1 : 1);
        quizzes.put(quiz.getId(), quiz);
        return quiz;
    }

    public Optional<Quiz> getOne(int id) {
        return Optional.ofNullable(quizzes.get(id));
    }

    public List<Quiz> getAll() {
        return new ArrayList<>(quizzes.values());
    }
}
