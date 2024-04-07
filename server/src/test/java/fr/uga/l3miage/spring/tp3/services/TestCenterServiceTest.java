package fr.uga.l3miage.spring.tp3.services;

import fr.uga.l3miage.spring.tp3.components.CandidateComponent;
import fr.uga.l3miage.spring.tp3.components.TestCenterComponent;
import fr.uga.l3miage.spring.tp3.exceptions.rest.UpdateTestCenterRestException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.CandidateNotFoundException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.TestCenterNotFoundException;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;

import fr.uga.l3miage.spring.tp3.request.TestCenterAddStudentsRequest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TestCenterServiceTest {
    @Autowired
    private TestCenterService testCenterService;

    @MockBean
    private TestCenterComponent testCenterComponent;

    @MockBean
    private CandidateComponent candidateComponent;


    @Test
    void testAddStudentColletionToTestCenter() throws TestCenterNotFoundException, CandidateNotFoundException {
        // Given
        CandidateEntity candidateEntity1 = CandidateEntity.builder()
                .id(10L)
                .email("test01@test.com")
                .birthDate(LocalDate.of(2012, 3, 5))
                .build();

        CandidateEntity candidateEntity2 = CandidateEntity.builder()
                .id(20L)
                .email("test02@test.com")
                .birthDate(LocalDate.of(2001, 3, 5))
                .build();

        CandidateEntity candidateEntity3 = CandidateEntity.builder()
                .id(30L)
                .email("test03@test.com")
                .birthDate(LocalDate.of(1999, 3, 5))
                .build();

        TestCenterEntity testCenterEntity = TestCenterEntity.builder()
                .university("Test UGA")
                .id(100L)
                .candidateEntities(Set.of())
                .build();

        TestCenterEntity testCenterEntityExpected = TestCenterEntity.builder()
                .university("Test UGA")
                .candidateEntities(Set.of(candidateEntity1, candidateEntity3))
                .build();

        when(testCenterComponent.getTestCenterById(100L)).thenReturn(testCenterEntity);
        when(candidateComponent.getCandidatsByIds(List.of(10L, 20L, 30L))).thenReturn(List.of(candidateEntity1, candidateEntity2, candidateEntity3));
        when(testCenterComponent.addStudents(any(TestCenterEntity.class), anySet())).thenReturn(true);

        // Utilisation d'ArgumentCaptor
        ArgumentCaptor<TestCenterEntity> testCenterCaptor = ArgumentCaptor.forClass(TestCenterEntity.class);

        TestCenterAddStudentsRequest request = TestCenterAddStudentsRequest.builder()
                .testCenterId(100L)
                .studentIds(List.of(10L, 20L, 30L))
                .build();

        // Then
        assertDoesNotThrow(() -> testCenterService.addStudents(request));

        verify(testCenterComponent, times(1)).getTestCenterById(any());
        verify(testCenterComponent, times(1)).addStudents(testCenterCaptor.capture(), anySet());
        verify(candidateComponent, times(1)).getCandidatsByIds(any());

        assertThat(testCenterCaptor.getValue()).isEqualTo(testCenterEntity);
    }


    @Test
    void testAddStudentColletionToTestCenter_ThrowsTestCenterNotFoundException() throws TestCenterNotFoundException, CandidateNotFoundException {
        // Given
        CandidateEntity candidateEntity1 = CandidateEntity.builder()
                .id(10L)
                .email("test01@test.com")
                .birthDate(LocalDate.of(2012, 3, 5))
                .build();

        CandidateEntity candidateEntity2 = CandidateEntity.builder()
                .id(20L)
                .email("test02@test.com")
                .birthDate(LocalDate.of(2001, 3, 5))
                .build();

        CandidateEntity candidateEntity3 = CandidateEntity.builder()
                .id(30L)
                .email("test03@test.com")
                .birthDate(LocalDate.of(1999, 3, 5))
                .build();

        TestCenterEntity testCenterEntity = TestCenterEntity.builder()
                .university("Test UGA")
                .id(100L)
                .candidateEntities(Set.of())
                .build();

        TestCenterEntity testCenterEntityExpected = TestCenterEntity.builder()
                .university("Test UGA")
                .candidateEntities(Set.of(candidateEntity1, candidateEntity3))
                .build();

        when(testCenterComponent.getTestCenterById(147L)).thenThrow(new TestCenterNotFoundException("testCenter avec l'ID " + 147L +" non trouvé", 147L));
        when(candidateComponent.getCandidatsByIds(List.of())).thenReturn(List.of());

        TestCenterAddStudentsRequest request = TestCenterAddStudentsRequest.builder()
                .testCenterId(147L)
                .studentIds(List.of())
                .build();

        //When-Then
        assertThrows(UpdateTestCenterRestException.class, () -> testCenterService.addStudents(request));
    }

    @Test
    void testAddStudentColletionToTestCenter_ThrowsCandidateNotFoundException() throws TestCenterNotFoundException, CandidateNotFoundException {
        // Given
        CandidateEntity candidateEntity1 = CandidateEntity.builder()
                .id(10L)
                .email("test01@test.com")
                .birthDate(LocalDate.of(2012, 3, 5))
                .build();

        CandidateEntity candidateEntity2 = CandidateEntity.builder()
                .id(20L)
                .email("test02@test.com")
                .birthDate(LocalDate.of(2001, 3, 5))
                .build();

        CandidateEntity candidateEntity3 = CandidateEntity.builder()
                .id(30L)
                .email("test03@test.com")
                .birthDate(LocalDate.of(1999, 3, 5))
                .build();

        TestCenterEntity testCenterEntity = TestCenterEntity.builder()
                .university("Test UGA")
                .id(100L)
                .candidateEntities(Set.of())
                .build();

        TestCenterEntity testCenterEntityExpected = TestCenterEntity.builder()
                .university("Test UGA")
                .candidateEntities(Set.of(candidateEntity1, candidateEntity3))
                .build();

        Long missingCandidateId = 404L;
        TestCenterAddStudentsRequest request = TestCenterAddStudentsRequest.builder()
                .testCenterId(147L)
                .studentIds(List.of(30L, missingCandidateId))
                .build();
        when(testCenterComponent.getTestCenterById(100L)).thenReturn(testCenterEntity);
        when(candidateComponent.getCandidatsByIds(List.of(30L, missingCandidateId))).thenThrow(new CandidateNotFoundException("Le candidat avec l'ID " + missingCandidateId + " existe pas", missingCandidateId));

        assertThrows(UpdateTestCenterRestException.class, () -> testCenterService.addStudents(request));
        // Je peux ajouter la comparaison des messages d'erreur...
    }

}
