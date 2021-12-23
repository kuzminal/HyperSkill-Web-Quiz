package engine.repository;

import engine.model.entity.CompletedQuiz;
import engine.model.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface CompletedQuizRepository extends PagingAndSortingRepository<CompletedQuiz, Integer> {
    @Query("SELECT m FROM CompletedQuiz m WHERE m.user.username=:userName ORDER BY m.completedAt DESC")
    Page<CompletedQuiz> findAllByUser(Pageable pageable, @Param("userName") String userName);
    void deleteByQuiz(Quiz quiz);
}
