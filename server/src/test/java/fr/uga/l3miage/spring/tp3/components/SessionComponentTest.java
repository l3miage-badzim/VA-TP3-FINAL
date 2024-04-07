package fr.uga.l3miage.spring.tp3.components;

import fr.uga.l3miage.spring.tp3.enums.SessionStatus;
import fr.uga.l3miage.spring.tp3.exceptions.technical.SessionConflitException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.SessionNotFoundException;
import fr.uga.l3miage.spring.tp3.models.EcosSessionEntity;
import fr.uga.l3miage.spring.tp3.models.EcosSessionProgrammationEntity;
import fr.uga.l3miage.spring.tp3.models.EcosSessionProgrammationStepEntity;
import fr.uga.l3miage.spring.tp3.repositories.EcosSessionProgrammationRepository;
import fr.uga.l3miage.spring.tp3.repositories.EcosSessionProgrammationStepRepository;
import fr.uga.l3miage.spring.tp3.repositories.EcosSessionRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SessionComponentTest {

    @Autowired
    private SessionComponent sessionComponent;

    @MockBean
    private EcosSessionRepository ecosSessionRepository;

    @MockBean
    private EcosSessionProgrammationRepository ecosSessionProgrammationRepository;

    @MockBean
    EcosSessionProgrammationStepRepository ecosSessionProgrammationStepRepository;

    @Test
    void testCreateSession() {
        //Given une ecoSessionEntity...
        EcosSessionProgrammationStepEntity ecosSessionProgrammationStepEntity = EcosSessionProgrammationStepEntity
                .builder()
                .code("001")
                .description("EcoSessionProgrammationStepDescriptionTest01")
                .build();

        EcosSessionProgrammationStepEntity ecosSessionProgrammationStepEntity1 = EcosSessionProgrammationStepEntity
                .builder()
                .code("002")
                .description("EcoSessionProgrammationStepDescriptionTest02")
                .build();

        EcosSessionProgrammationEntity ecosSessionProgrammationEntity = EcosSessionProgrammationEntity
                .builder()
                .label("EcosSessionProgrammationTest01")
                .ecosSessionProgrammationStepEntities(Set.of(ecosSessionProgrammationStepEntity,ecosSessionProgrammationStepEntity1 ))
                .build();

        EcosSessionEntity ecosSessionEntity = EcosSessionEntity
                .builder()
                .name("EcosSessionTest01")
                .ecosSessionProgrammationEntity(ecosSessionProgrammationEntity)
                .build();

        ecosSessionProgrammationStepEntity.setEcosSessionProgrammationEntity(ecosSessionProgrammationEntity);
        ecosSessionProgrammationStepEntity1.setEcosSessionProgrammationEntity(ecosSessionProgrammationEntity);

        when(ecosSessionProgrammationRepository.save(any(EcosSessionProgrammationEntity.class))).thenReturn(ecosSessionProgrammationEntity);
        when(ecosSessionRepository.save(any(EcosSessionEntity.class))).thenReturn(ecosSessionEntity);
        when(ecosSessionProgrammationStepRepository.saveAll(anySet())).thenReturn(Arrays.asList(ecosSessionProgrammationStepEntity, ecosSessionProgrammationStepEntity1));

        //When
        EcosSessionEntity ecosSessionEntityResult = sessionComponent.createSession(ecosSessionEntity);

        //Then
        assertNotNull(ecosSessionEntityResult);
        assertEquals("EcosSessionTest01", ecosSessionEntityResult.getName());


        verify(ecosSessionProgrammationRepository).save(any(EcosSessionProgrammationEntity.class));
        verify(ecosSessionRepository).save(any(EcosSessionEntity.class));
        verify(ecosSessionProgrammationStepRepository).saveAll(anySet());



    }

    @Test
    void testChangeStatus() throws SessionConflitException, SessionNotFoundException {
        // Given
        EcosSessionEntity ecosSessionEntity = EcosSessionEntity.builder()
                .name("EcosSessionTest01")
                .status(SessionStatus.EVAL_STARTED)
                .examEntities(Set.of())
                .build();
        ecosSessionRepository.save(ecosSessionEntity);
        when(ecosSessionRepository.findById(anyLong())).thenReturn(Optional.of(ecosSessionEntity));

        // When
        sessionComponent.changeStatus(ecosSessionEntity.getId());

        // Then
        verify(ecosSessionRepository, times(1)).findById(ecosSessionEntity.getId());
        verify(ecosSessionRepository, times(1)).save(ecosSessionEntity);
    }


    @Test
    void testChangeStatusFromWrongPreviousState() {
        // Given
        EcosSessionEntity ecosSessionEntity = EcosSessionEntity.builder()
                .name("EcosSessionTest01")
                .status(SessionStatus.CREATED)
                .examEntities(Set.of())
                .build();
        when(ecosSessionRepository.findById(ecosSessionEntity.getId())).thenReturn(Optional.of(ecosSessionEntity));

        // Then
        assertThrows(SessionConflitException.class, () -> sessionComponent.changeStatus(ecosSessionEntity.getId()));
    }

    @Test
    void testChangeStatusSessionNotFound() {
        // Given
        when(ecosSessionRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        assertThrows(SessionNotFoundException.class, () -> sessionComponent.changeStatus(anyLong()));
    }
}
