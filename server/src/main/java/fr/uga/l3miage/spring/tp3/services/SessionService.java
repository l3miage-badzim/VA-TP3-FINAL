package fr.uga.l3miage.spring.tp3.services;

import fr.uga.l3miage.spring.tp3.components.ExamComponent;
import fr.uga.l3miage.spring.tp3.components.SessionComponent;
import fr.uga.l3miage.spring.tp3.enums.SessionStatus;
import fr.uga.l3miage.spring.tp3.exceptions.rest.ChangingStatusRestException;
import fr.uga.l3miage.spring.tp3.exceptions.rest.CreationSessionRestException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.ExamNotFoundException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.SessionConflitException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.SessionNotFoundException;
import fr.uga.l3miage.spring.tp3.mappers.CandidateEvaluationGridMapper;
import fr.uga.l3miage.spring.tp3.mappers.SessionMapper;
import fr.uga.l3miage.spring.tp3.models.*;
import fr.uga.l3miage.spring.tp3.request.SessionCreationRequest;
import fr.uga.l3miage.spring.tp3.responses.CandidateEvaluationGridResponse;
import fr.uga.l3miage.spring.tp3.responses.SessionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionMapper sessionMapper;
    private final CandidateEvaluationGridMapper candidateEvaluationGridMapper;

    private final ExamComponent examComponent;
    private final SessionComponent sessionComponent;

    public SessionResponse createSession(SessionCreationRequest sessionCreationRequest){
        try {
            EcosSessionEntity ecosSessionEntity = sessionMapper.toEntity(sessionCreationRequest);
            EcosSessionProgrammationEntity programmation = sessionMapper.toEntity(sessionCreationRequest.getEcosSessionProgrammation());
            Set<EcosSessionProgrammationStepEntity> stepEntities = sessionCreationRequest.getEcosSessionProgrammation()
                    .getSteps()
                    .stream()
                    .map(sessionMapper::toEntity)
                    .collect(Collectors.toSet());

            Set<ExamEntity> exams = examComponent.getAllById(sessionCreationRequest.getExamsId());

            ecosSessionEntity.setExamEntities(exams);
            programmation.setEcosSessionProgrammationStepEntities(stepEntities);
            ecosSessionEntity.setEcosSessionProgrammationEntity(programmation);

            ecosSessionEntity.setStatus(SessionStatus.CREATED);

            return sessionMapper.toResponse(sessionComponent.createSession(ecosSessionEntity));
        }catch (RuntimeException | ExamNotFoundException e){
            throw new CreationSessionRestException(e.getMessage());
        }
    }

    public List<CandidateEvaluationGridResponse> changeStatus(Long idSession) {
        try {
            List<CandidateEvaluationGridEntity> evaluationGrids = sessionComponent.changeStatus(idSession);
            List<CandidateEvaluationGridResponse> result = new ArrayList<>();
            for (CandidateEvaluationGridEntity grid : evaluationGrids) {
                result.add(candidateEvaluationGridMapper.toResponse(grid));
            }
            return result;
        } catch (SessionNotFoundException | SessionConflitException e) {
            throw new ChangingStatusRestException(e.getMessage());
        }
    }
}
