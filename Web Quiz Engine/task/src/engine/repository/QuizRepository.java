package engine.repository;

import engine.model.entity.Quiz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer> {
    @Query("SELECT m FROM Quiz m WHERE m.user.username=:userName ORDER BY m.title DESC")
    List<Quiz> findAllByUser(@Param("userName") String userName);
    @Query("SELECT m FROM Quiz m WHERE m.user.username=:userName AND m.id=:id")
    Optional<Quiz> findByUser(@Param("id") int id, @Param("userName") String userName);
    @Query("SELECT q FROM Quiz q JOIN FETCH q.user WHERE q.id = ?1 and q.user.username = ?2")
    Optional<Quiz> findWithUser(int id, String userName);
    /*@Modifying
    @Transactional
    @Query("DELETE FROM Quiz m WHERE m.id=:id AND m.user.username=:userName")
    int delete(@Param("id") int id, @Param("userName") String userName);*/
}
