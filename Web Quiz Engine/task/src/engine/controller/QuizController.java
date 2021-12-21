package engine.controller;

import engine.model.Quiz;
import engine.model.Result;
import engine.service.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public Quiz getQuiz() {
        return quizService.getQuiz();
    }

    @PostMapping
    public Result answer(@RequestParam(name = "answer", required = false)Optional<Integer> answer) {
        return quizService.checkAnswer(answer);
    }
}
