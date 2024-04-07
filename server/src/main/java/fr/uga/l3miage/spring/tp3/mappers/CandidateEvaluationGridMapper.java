package fr.uga.l3miage.spring.tp3.mappers;

import fr.uga.l3miage.spring.tp3.models.CandidateEvaluationGridEntity;
import fr.uga.l3miage.spring.tp3.responses.CandidateEvaluationGridResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ExamMapper.class})
public interface CandidateEvaluationGridMapper {



    CandidateEvaluationGridResponse toResponse(CandidateEvaluationGridEntity entity);

    // Vous pouvez ajouter des méthodes supplémentaires ici pour mapper dans l'autre sens si nécessaire
    // ou pour gérer des cas de mapping plus complexes.
}
