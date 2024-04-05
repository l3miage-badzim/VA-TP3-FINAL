package fr.uga.l3miage.spring.tp3.repositories;


import fr.uga.l3miage.spring.tp3.enums.TestCenterCode;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.CandidateEvaluationGridEntity;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class CandidateRepositoryTest {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private TestCenterRepository testCenterRepository;

    @Autowired
    private CandidateEvaluationGridRepository candidateEvaluationGridRepository;

    @Test
    void testRequestFindAllByTestCenterEntityCode() {
        //Given
        TestCenterEntity testCenterEntity = TestCenterEntity
                .builder()
                .code(TestCenterCode.GRE)
                .candidateEntities(Set.of())
                .build();

        TestCenterEntity testCenterEntity1 = TestCenterEntity
                .builder()
                .code(TestCenterCode.NCE)
                .build();

        CandidateEntity candidateEntity1 = CandidateEntity
                .builder()
                .email("test01@test.com")
                .testCenterEntity(testCenterEntity)
                .build();

        CandidateEntity candidateEntity2 = CandidateEntity
                .builder()
                .email("test02@test.com")
                .testCenterEntity(testCenterEntity1)
                .build();

        testCenterRepository.save(testCenterEntity);
        testCenterRepository.save(testCenterEntity1);
        candidateRepository.save(candidateEntity1);
        candidateRepository.save(candidateEntity2);

        //When
        Set<CandidateEntity> candidateEntityResponse = candidateRepository.findAllByTestCenterEntityCode(TestCenterCode.GRE);

        //Then
        assertThat(candidateEntityResponse).hasSize(1);
        assertThat(candidateEntityResponse.stream().findFirst().get().getTestCenterEntity().getCode()).isEqualTo(TestCenterCode.GRE);
    }
  
    @Test
    void testRequestFindAllByCandidateEvaluationGridEntitiesGradeLessThan(){

        CandidateEvaluationGridEntity candidateEvaluationGridEntity = CandidateEvaluationGridEntity
                .builder()
                .grade(5)
                .build();

        CandidateEvaluationGridEntity candidateEvaluationGridEntity1 = CandidateEvaluationGridEntity
                .builder()
                .grade(3)
                .build();

        candidateEvaluationGridRepository.save(candidateEvaluationGridEntity);
        candidateEvaluationGridRepository.save(candidateEvaluationGridEntity1);

        CandidateEntity candidateEntity = CandidateEntity
                .builder()
                .firstname("firstname")
                .candidateEvaluationGridEntities(Set.of(candidateEvaluationGridEntity))
                .build();

        CandidateEntity candidateEntity1 = CandidateEntity
                .builder()
                .firstname("firstname")
                .candidateEvaluationGridEntities(Set.of(candidateEvaluationGridEntity1))
                .build();

        candidateRepository.save(candidateEntity);
        candidateRepository.save(candidateEntity1);        
    }
}
