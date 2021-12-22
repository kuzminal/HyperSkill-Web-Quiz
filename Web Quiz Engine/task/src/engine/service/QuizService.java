package engine.service;

import engine.model.dto.Answer;
import engine.model.entity.Quiz;
import engine.model.dto.Result;
import engine.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Result checkAnswer(int quizId, Answer answer) {
        Optional<Quiz> quiz = getOne(quizId);
        if (quiz.isPresent()) {
            if (answer.getAnswer().equals(
                    quiz.get().getAnswer() == null ? List.of() : quiz.get().getAnswer())
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
        return quizRepository.save(quiz);
    }

    public Optional<Quiz> getOne(int id) {
        return quizRepository.findById(id);
    }

    public List<Quiz> getAll() {
        return quizRepository.findAll();
    }
}
