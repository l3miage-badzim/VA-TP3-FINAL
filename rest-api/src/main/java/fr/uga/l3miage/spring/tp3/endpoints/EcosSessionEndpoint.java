package fr.uga.l3miage.spring.tp3.endpoints;

import fr.uga.l3miage.spring.tp3.errors.ChangeSessionStatusErrorResponse;
import fr.uga.l3miage.spring.tp3.responses.SessionResponse;
import fr.uga.l3miage.spring.tp3.responses.enums.SessionStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name="Gestion des EcosSession")
@RestController
@RequestMapping("/api/EcosSession")
public interface EcosSessionEndpoint {

    @Operation(description = "Changer le status d'une session")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "409", description = "CONFLIT",content = @Content(schema = @Schema(implementation = ChangeSessionStatusErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idSession}")
    SessionResponse changeStatus(@PathVariable(name = "idSession") Long idSession, SessionStatus status);

}
