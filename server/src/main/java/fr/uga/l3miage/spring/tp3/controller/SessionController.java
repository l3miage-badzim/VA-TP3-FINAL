package fr.uga.l3miage.spring.tp3.controller;

import fr.uga.l3miage.spring.tp3.endpoints.SessionEndpoints;
import fr.uga.l3miage.spring.tp3.request.SessionCreationRequest;
import fr.uga.l3miage.spring.tp3.responses.CandidateEvaluationGridResponse;
import fr.uga.l3miage.spring.tp3.responses.SessionResponse;
import fr.uga.l3miage.spring.tp3.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SessionController implements SessionEndpoints {
    private final SessionService sessionService;

    @Override
    public SessionResponse createSession(SessionCreationRequest request) {
        return sessionService.createSession(request);
    }

    @Override
    public List<CandidateEvaluationGridResponse> changeStatus(Long idSession) {
        return sessionService.changeStatus(idSession);
    }
}
