package engine.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CompletedQuizDto {
    Integer id;
    LocalDateTime completedAt;
}
