package engine.repository;

import engine.model.entity.Quiz;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {
    List<Quiz> findAll();
}
