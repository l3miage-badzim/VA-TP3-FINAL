package fr.uga.l3miage.spring.tp3.mappers;

import fr.uga.l3miage.spring.tp3.models.CandidateEvaluationGridEntity;
import fr.uga.l3miage.spring.tp3.responses.CandidateEvaluationGridResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ExamMapper.class})
public interface CandidateEvaluationGridMapper {



        @Mapping(source = "candidateEntity", target = "candidateEntity")
        @Mapping(source = "examinerEntity", target = "examinerEntity")
        @Mapping(source = "examEntity", target = "examEntity")// Ignore ce champ
        CandidateEvaluationGridResponse toResponse(CandidateEvaluationGridEntity entity);
    }


