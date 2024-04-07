package fr.uga.l3miage.spring.tp3.components;

import fr.uga.l3miage.spring.tp3.enums.SessionStatus;
import fr.uga.l3miage.spring.tp3.exceptions.technical.SessionConflitException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.SessionNotFoundException;
import fr.uga.l3miage.spring.tp3.models.CandidateEvaluationGridEntity;
import fr.uga.l3miage.spring.tp3.models.EcosSessionEntity;
import fr.uga.l3miage.spring.tp3.models.ExamEntity;
import fr.uga.l3miage.spring.tp3.repositories.*;
import fr.uga.l3miage.spring.tp3.responses.CandidateEvaluationGridResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SessionComponent {
    private final EcosSessionRepository ecosSessionRepository;
    private final EcosSessionProgrammationRepository ecosSessionProgrammationRepository;
    private final EcosSessionProgrammationStepRepository ecosSessionProgrammationStepRepository;



    public EcosSessionEntity createSession(EcosSessionEntity entity){
        ecosSessionProgrammationStepRepository.saveAll(entity.getEcosSessionProgrammationEntity().getEcosSessionProgrammationStepEntities());
        ecosSessionProgrammationRepository.save(entity.getEcosSessionProgrammationEntity());
        return ecosSessionRepository.save(entity);
    }


    public List<CandidateEvaluationGridEntity> changeStatus(Long sessionId) throws SessionNotFoundException, SessionConflitException {
        EcosSessionEntity session = ecosSessionRepository.findById(sessionId).orElseThrow(() -> new SessionNotFoundException("session not found"));
        if (session.getStatus() != SessionStatus.EVAL_STARTED) {
            throw new SessionConflitException("État précédant bizarre");
        }

        Set<ExamEntity> exams = session.getExamEntities();
        List<CandidateEvaluationGridEntity> results = new ArrayList<>(); // Utilisez une ArrayList modifiable
        for (ExamEntity exam : exams) {
            results.addAll(exam.getCandidateEvaluationGridEntities());
        }

        session.setStatus(SessionStatus.EVAL_ENDED);
        ecosSessionRepository.save(session);

        return results;
    }

}
