package engine.controller;

import engine.model.dto.Answer;
import engine.model.dto.CompletedQuizDto;
import engine.model.dto.Result;
import engine.model.entity.Quiz;
import engine.model.entity.User;
import engine.service.QuizService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable("id") Integer quizId) {
        return quizService.getOne(quizId)
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Quiz>> getAllQuizzes(@RequestParam(defaultValue = "0", name = "page") Integer pageNo,
                                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                                    @RequestParam(defaultValue = "title") String sortBy) {
        return ResponseEntity.ok().body(quizService.getAll(pageNo, pageSize, sortBy));
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<Result> answer(@PathVariable("id") Integer quizId,
                                         @Valid @RequestBody Answer answer,
                                         @AuthenticationPrincipal User user) {
        Result result = quizService.checkAnswer(quizId, answer, user);
        if (result != null) {
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Quiz> saveQuiz(@Valid @RequestBody Quiz quiz,
                                         @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(quizService.saveQuiz(quiz, user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable("id") Integer quizId,
                                        @AuthenticationPrincipal User user) {
        Optional<Quiz> quiz = quizService.getOne(quizId);
        if (quiz.isPresent() && quiz.get().getUser().getUsername().equals(user.getUsername())) {
            quizService.deleteQuiz(quiz.get());
            return ResponseEntity.noContent().build();
        } else if (quiz.isPresent() && !quiz.get().getUser().getUsername().equals(user.getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/completed")
    public ResponseEntity<Page<CompletedQuizDto>> getAllCompleted(@RequestParam(defaultValue = "0", name = "page") Integer pageNo,
                                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                                  @RequestParam(defaultValue = "completedAt") String sortBy,
                                                                  @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(quizService.getAllCompleted(pageNo, pageSize, sortBy, user));
    }
}
