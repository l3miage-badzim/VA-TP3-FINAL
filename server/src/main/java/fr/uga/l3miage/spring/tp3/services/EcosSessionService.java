package fr.uga.l3miage.spring.tp3.services;

import fr.uga.l3miage.spring.tp3.components.EcosSessionComponent;
import fr.uga.l3miage.spring.tp3.enums.SessionStatus;
import fr.uga.l3miage.spring.tp3.exceptions.rest.ChangingStatusRestException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.SessionNotFoundException;
import fr.uga.l3miage.spring.tp3.mappers.EcosSessionMapper;
import fr.uga.l3miage.spring.tp3.models.EcosSessionEntity;
import fr.uga.l3miage.spring.tp3.responses.EcosSessionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EcosSessionService {
    private final EcosSessionComponent ecosSessionComponent;
    private final EcosSessionMapper ecosSessionMapper;

    public EcosSessionResponse changeSessionStatus(Long idSession, SessionStatus status){
        try {
            EcosSessionEntity ecosSessionEntity = EcosSessionComponent.changeStatus(idSession, status);
            return EcosSessionMapper.toResponse(ecosSessionEntity);
        } catch (SessionNotFoundException e){
            throw new ChangingStatusRestException(e.getMessage());
        }
    }
}
