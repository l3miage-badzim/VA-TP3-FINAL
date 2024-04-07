package fr.uga.l3miage.spring.tp3.exceptions;

import fr.uga.l3miage.spring.tp3.responses.enums.SessionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SessionConflitErrorResponse {
    @Schema(description = "lien d'appel de l'api", example = "api/drone/")
    private final String uri;
    @Schema(description = "message d'erreur", example = "Le status n'a pas été changé")
    private final String errorMessage;
    @Schema(description = "L'état actuel de la session")
    private final SessionStatus actualStatus;
}
