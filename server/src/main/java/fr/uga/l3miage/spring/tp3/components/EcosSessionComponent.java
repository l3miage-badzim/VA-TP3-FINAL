package fr.uga.l3miage.spring.tp3.components;

import fr.uga.l3miage.spring.tp3.enums.SessionStatus;
import fr.uga.l3miage.spring.tp3.exceptions.technical.SessionNotFoundException;
import fr.uga.l3miage.spring.tp3.models.EcosSessionEntity;
import fr.uga.l3miage.spring.tp3.repositories.EcosSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EcosSessionComponent {
    private final EcosSessionRepository ecosSessionRepository;

    public static EcosSessionEntity changeStatus(Long idSession, SessionStatus status) throws SessionNotFoundException {
        EcosSessionEntity ecosSessionEntity = ecosSessionRepository.findById(idSession)
                .orElseThrow(()->new SessionNotFoundException(String.format("La session %s n'a pas été trouvé", idSession)));
        ecosSessionEntity.setStatus(status);
        return ecosSessionRepository.save(ecosSessionEntity);

    }
}
