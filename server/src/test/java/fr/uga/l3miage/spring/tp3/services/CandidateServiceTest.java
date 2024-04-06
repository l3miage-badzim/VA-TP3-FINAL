package fr.uga.l3miage.spring.tp3.services;

import fr.uga.l3miage.spring.tp3.components.CandidateComponent;
import fr.uga.l3miage.spring.tp3.exceptions.technical.CandidateNotFoundException;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.CandidateEvaluationGridEntity;
import fr.uga.l3miage.spring.tp3.models.ExamEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Set;

import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CandidateServiceTest {
    @Autowired
    private CandidateService candidateService;

    @MockBean
    private CandidateComponent candidateComponent;
    @SpyBean
    private CandidateEntity candidateEntity;

    @Test
    void getCandidateAverage() throws CandidateNotFoundException {

        // Given
        Long candidateId = 1L;
        Set<CandidateEvaluationGridEntity> evaluationGridEntities = Set.of(new CandidateEvaluationGridEntity());
        candidateEntity.setCandidateEvaluationGridEntities(evaluationGridEntities);

        ExamEntity examEntity = new ExamEntity();
        examEntity.setWeight(1);
        for (CandidateEvaluationGridEntity gridEntity : evaluationGridEntities) {
            gridEntity.setExamEntity(examEntity);
        }

        double expectedAverage = 85.0;
        when(candidateComponent.getCandidatById(candidateId)).thenReturn(candidateEntity);

        // When
        Double result = candidateService.getCandidateAverage(candidateId);


        // Then
        verify(candidateComponent, times(1)).getCandidatById(candidateId);

    }
}
