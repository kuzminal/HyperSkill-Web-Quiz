package engine.service;

import engine.model.dto.Answer;
import engine.model.dto.CompletedQuizDto;
import engine.model.dto.Result;
import engine.model.entity.CompletedQuiz;
import engine.model.entity.Quiz;
import engine.model.entity.User;
import engine.repository.CompletedQuizRepository;
import engine.repository.QuizRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final CompletedQuizRepository completedQuizRepository;

    public QuizService(QuizRepository quizRepository, CompletedQuizRepository completedQuizRepository) {
        this.quizRepository = quizRepository;
        this.completedQuizRepository = completedQuizRepository;
    }

    public Result checkAnswer(int quizId, Answer answer, User user) {
        Optional<Quiz> quiz = getOne(quizId);
        if (quiz.isPresent()) {
            if (answer.getAnswer().equals(
                    quiz.get().getAnswer() == null ? List.of() : quiz.get().getAnswer())
            ) {
                CompletedQuiz completedQuiz = CompletedQuiz.builder()
                        .quiz(quiz.get())
                        .user(user)
                        .completedAt(LocalDateTime.now())
                        .build();
                completedQuizRepository.save(completedQuiz);
                return new Result(true, "Congratulations, you're right!");
            } else {
                return new Result(false, "Wrong answer! Please, try again.");
            }
        } else {
            return null;
        }
    }

    public Quiz saveQuiz(Quiz quiz, User user) {
        quiz.setUser(user);
        return quizRepository.save(quiz);
    }

    public Optional<Quiz> getOne(int id) {
        return quizRepository.findById(id);
    }

    public Page<Quiz> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        return quizRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortBy)));
    }

    public void deleteQuiz(Quiz quiz) {
        quizRepository.delete(quiz);
    }

    public Page<CompletedQuizDto> getAllCompleted(Integer pageNo, Integer pageSize, String sortBy, User user) {
        Page<CompletedQuiz> page = completedQuizRepository.findAllByUser(PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending()), user.getUsername());
        page.map(this::convertToCompletedQuizDto);
        return page.map(this::convertToCompletedQuizDto);
    }

    private CompletedQuizDto convertToCompletedQuizDto(CompletedQuiz completedQuiz) {
        return CompletedQuizDto.builder()
                .id(completedQuiz.getQuiz().getId())
                .completedAt(completedQuiz.getCompletedAt())
                .build();
    }
}
