package fr.uga.l3miage.spring.tp3.components;

import fr.uga.l3miage.spring.tp3.exceptions.technical.CandidateNotFoundException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.ExamNotFoundException;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.ExamEntity;
import fr.uga.l3miage.spring.tp3.repositories.ExamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)

public class ExamComponentTest {
    @Autowired
    private ExamComponent examComponent;
    @MockBean
    private ExamRepository examRepository;

    @Test
    void getAllNotFound(){
        //Given
        when(examRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when - then
        assertThrows(ExamNotFoundException.class, () -> examComponent.getAllById(Set.of(1L, 5L, 9L)));
    }
    @Test
    void getAllFound(){
        //given
        ExamEntity examEntity = ExamEntity
                .builder()
                .id(5L)
                .build();
        when(examRepository.findById(5L)).thenReturn(Optional.of(examEntity));

        ExamEntity examEntity2 = ExamEntity
                .builder()
                .id(5L)
                .build();
        when(examRepository.findById(5L)).thenReturn(Optional.of(examEntity2));

        //when - then
        assertDoesNotThrow(()->examComponent.getAllById(Set.of(5L)));
    }
}
