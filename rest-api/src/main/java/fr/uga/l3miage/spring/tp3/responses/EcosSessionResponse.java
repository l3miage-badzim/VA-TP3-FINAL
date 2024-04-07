package fr.uga.l3miage.spring.tp3.responses;

import fr.uga.l3miage.spring.tp3.responses.enums.SessionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class EcosSessionResponse {
    @Schema(description = "id du session")
    private Long id;
    @Schema(description = "nom du session")
    private String name;
    @Schema(description = "date de début du session")
    private LocalDateTime startDate;
    @Schema(description = "date de fin du session")
    private LocalDateTime endDate;
    @Schema(description = "status du session")
    private SessionStatus status;

    @Schema(description = "La liste des exams dans la session")
    private Set<ExamResponse> examEntities;

    @Schema(description = "programmation du session")
    private EcosSessionProgrammationResponse ecosSessionProgrammationEntity;
}
