package fr.uga.l3miage.spring.tp3.repositories;


import fr.uga.l3miage.spring.tp3.enums.TestCenterCode;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
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

    @Test
    void testRequestFindAllByTestCenterEntityCode() {
        //Given
        TestCenterEntity testCenterEntity = TestCenterEntity
                .builder()
                .code(TestCenterCode.GRE)
                .candidateEntities(Set.of())
                .build();

        CandidateEntity candidateEntity1 = CandidateEntity
                .builder()
                .email("test01@test.com")
                .testCenterEntity(testCenterEntity)
                .build();

        TestCenterEntity testCenterEntity1 = TestCenterEntity
                .builder()
                .code(TestCenterCode.DIJ)
                .candidateEntities(Set.of())
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
}
