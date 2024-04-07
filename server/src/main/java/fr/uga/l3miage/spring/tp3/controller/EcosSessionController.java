package fr.uga.l3miage.spring.tp3.controller;

import fr.uga.l3miage.spring.tp3.endpoints.EcosSessionEndpoint;
import fr.uga.l3miage.spring.tp3.responses.SessionResponse;
import fr.uga.l3miage.spring.tp3.responses.enums.SessionStatus;
import fr.uga.l3miage.spring.tp3.services.EcosSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class EcosSessionController implements EcosSessionEndpoint {
    private final EcosSessionService ecosSessionService;
    @Override
    public SessionResponse changeStatus(Long idSession, SessionStatus status) {
        return null;
    }
}
