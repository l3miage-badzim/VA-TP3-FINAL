package fr.uga.l3miage.spring.tp3.endpoints;

import fr.uga.l3miage.spring.tp3.exceptions.TestCenterOrCandidatInListNotFoundResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@Tag(name="Gestion testCenter", description = "Tous les endpoints de gestion d'un testCenter")
@RestController
@RequestMapping("/api/testCenter")
public interface TestCenterEndpoints {

    @Operation(description = "ajouter une collection d'étudiant au testCenter")
    @ApiResponse(responseCode = "202", description = "la liste d'étudiants à bien été ajoute")
    @ApiResponse(responseCode = "404", description = "soit un des étudiants de la liste n'as pas été trouvé soit le testCenter n'existe pas", content = @Content(schema = @Schema(implementation = TestCenterOrCandidatInListNotFoundResponse.class ), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "400" ,description = "Le testCenter n'as pas pu être modifié", content = @Content(schema = @Schema(implementation = String.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{idTestCenter}/add")
    boolean addStudentColletionInTestCenter(@PathVariable(name = "idTestCenter")Long idTestCenter, @RequestParam List<Long> listIdStudents);
}
