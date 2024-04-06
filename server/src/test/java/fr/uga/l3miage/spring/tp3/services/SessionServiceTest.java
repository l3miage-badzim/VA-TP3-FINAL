package fr.uga.l3miage.spring.tp3.services;

import fr.uga.l3miage.spring.tp3.components.ExamComponent;
import fr.uga.l3miage.spring.tp3.components.SessionComponent;
import fr.uga.l3miage.spring.tp3.mappers.SessionMapper;
import fr.uga.l3miage.spring.tp3.models.EcosSessionEntity;
import fr.uga.l3miage.spring.tp3.request.SessionCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDateTime;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static  org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    @SpyBean
    private SessionMapper sessionMapper;

    @MockBean
    private ExamComponent examComponent;

    @MockBean
    private SessionComponent sessionComponent;

    void testCreateSession() {
        //Given

        // j'ai tout d'abord besoin d'une request... qui contient les informations sur la request de creation de la request...
        SessionCreationRequest sessionCreationRequest = SessionCreationRequest
                .builder()
                .name("test01")
                .startDate(LocalDateTime.of(2024, 3, 10, 8, 30))
                .endDate(LocalDateTime.of(2024, 3, 10, 18, 30))
                .examsId(Set.of(1L, 2L, 3L))
                .build();





    }

}
