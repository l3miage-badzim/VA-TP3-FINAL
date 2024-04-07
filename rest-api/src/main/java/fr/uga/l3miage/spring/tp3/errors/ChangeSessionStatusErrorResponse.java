package fr.uga.l3miage.spring.tp3.errors;

import fr.uga.l3miage.spring.tp3.responses.enums.SessionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChangeSessionStatusErrorResponse {
    @Schema(description = "end point call", example = "api/drone/")
    private final String uri;
    @Schema(description = "error message", example = "Le status n'a pas été changé")
    private final String errorMessage;
    @Schema(description = "état actuel de la session")
    private final SessionStatus actualStatus;
}
