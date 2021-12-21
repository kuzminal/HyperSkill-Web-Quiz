package engine.controller;

import engine.model.Answer;
import engine.model.Quiz;
import engine.model.Result;
import engine.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable("id") int quizId) {
        return quizService.getOne(quizId)
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok().body(quizService.getAll());
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<Result> answer(@PathVariable("id") int quizId, @Valid @RequestBody Answer answer) {
        Result result = quizService.checkAnswer(quizId, answer);
        if (result != null) {
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Quiz> saveQuiz(@Valid @RequestBody Quiz quiz) {
        return ResponseEntity.ok().body(quizService.saveQuiz(quiz));
    }
}
