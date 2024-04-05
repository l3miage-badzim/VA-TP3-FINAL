package fr.uga.l3miage.spring.tp3.repositories;


import fr.uga.l3miage.spring.tp3.enums.TestCenterCode;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@AutoConfigureWebTestClient
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
                .build();
        testCenterRepository.save(testCenterEntity);

        CandidateEntity candidateEntity1 = CandidateEntity
                .builder()
                .firstname("test")
                .lastname("test")
                .email("test@test.com")
                .testCenterEntity(testCenterEntity)
                .build();

//        CandidateEntity candidateEntity2 = CandidateEntity
//                .builder()
//                .firstname("test")
//                .lastname("test")
//                .email("test@test.com")
//                .testCenterEntity(new TestCenterEntity())
//                .build();


        candidateRepository.save(candidateEntity1);
//        candidateRepository.save(candidateEntity2);

        //When
        Set<CandidateEntity> candidateEntitySet = candidateRepository.findAllByTestCenterEntityCode(TestCenterCode.GRE);

        //Then
        assertThat(candidateEntitySet).hasSize(1);



    }
}
