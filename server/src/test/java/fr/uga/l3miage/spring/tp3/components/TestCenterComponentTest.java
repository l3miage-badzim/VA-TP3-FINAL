package fr.uga.l3miage.spring.tp3.components;

import fr.uga.l3miage.spring.tp3.exceptions.technical.TestCenterNotFoundException;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import fr.uga.l3miage.spring.tp3.repositories.CandidateRepository;
import fr.uga.l3miage.spring.tp3.repositories.TestCenterRepository;


import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TestCenterComponentTest {
    @Autowired
    private TestCenterComponent testCenterComponent;

    @MockBean
    private TestCenterRepository testCenterRepository;

    @MockBean
    private CandidateRepository candidateRepository;

    @Test
    void testFoundGetTestCenterById() {
        // Given
        TestCenterEntity testCenterEntity = TestCenterEntity.builder()
                .university("test UGA")
                .build();

        when(testCenterRepository.findById(anyLong())).thenReturn(Optional.of(testCenterEntity));

        // When - Then
        assertDoesNotThrow(() -> testCenterComponent.getTestCenterById(0L));
    }

    @Test
    void testNotFoundGetTestCenterById() {
        // Given

        when(testCenterRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When - Then
        assertThrows(TestCenterNotFoundException.class, () -> testCenterComponent.getTestCenterById(0L));
    }

    @Test
    void testAddStudentColletionInTestCenter() throws TestCenterNotFoundException{
        // Given
        CandidateEntity candidateEntity1 = CandidateEntity.builder()
                .email("test01@test.com")
                .birthDate(LocalDate.of(2012, 3, 5))
                .build();

        CandidateEntity candidateEntity2 = CandidateEntity.builder()
                .email("test02@test.com")
                .birthDate(LocalDate.of(2001, 3, 5))
                .build();

        CandidateEntity candidateEntity3 = CandidateEntity.builder()
                .email("test03@test.com")
                .build();

        TestCenterEntity testCenterEntity = TestCenterEntity.builder()
                .university("Test UGA")
                .id(100L)
                .candidateEntities(Set.of())
                .build();

        TestCenterEntity testCenterEntityExpected = TestCenterEntity.builder()
                .university("Test UGA")
                .candidateEntities(Set.of(candidateEntity1, candidateEntity2))
                .build();

        when(candidateRepository.save(any(CandidateEntity.class))).thenReturn(candidateEntity1);

        // Utilisation d'ArgumentCaptor
        ArgumentCaptor<CandidateEntity> candidateCaptor = ArgumentCaptor.forClass(CandidateEntity.class);

        // When
        boolean result = testCenterComponent.addStudentColletionToTestCenter(testCenterEntity, Set.of(candidateEntity1, candidateEntity2));

        // Then

        verify(candidateRepository, times(1)).save(candidateCaptor.capture());
        List<CandidateEntity> capturedCandidates = candidateCaptor.getAllValues();

        assertTrue(result);
        assertEquals(1, capturedCandidates.size());
        assertTrue(capturedCandidates.stream().allMatch(c -> c.getTestCenterEntity().equals(testCenterEntity))); // Vérifie que chaque étudiant est associé au bon centre de test
        assertTrue(capturedCandidates.stream().allMatch(c -> Period.between(c.getBirthDate(),LocalDate.now()).getYears() >= 18)); // Vérifie que chaque étudiant est associé au bon centre de test

        // je dois ajouter aussi la verification d'age




    }


}
