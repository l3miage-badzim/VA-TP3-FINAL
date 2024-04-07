package fr.uga.l3miage.spring.tp3.components;

import fr.uga.l3miage.spring.tp3.exceptions.technical.TestCenterNotFoundException;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import fr.uga.l3miage.spring.tp3.repositories.CandidateRepository;
import fr.uga.l3miage.spring.tp3.repositories.TestCenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TestCenterComponent {
    private final TestCenterRepository testCenterRepository;
    private final CandidateRepository candidateRepository;

    public TestCenterEntity getTestCenterById(Long id) throws TestCenterNotFoundException {
        return testCenterRepository.findById(id).orElseThrow(() -> new TestCenterNotFoundException("testCenter avec l'ID " + id +" non trouvé", id));
    }

    public boolean addStudentColletionToTestCenter(TestCenterEntity testCenterEntity, Set<CandidateEntity> listStudents){
        for (CandidateEntity student : listStudents) {
            // je dois ajouter la logique pour verifier que seul les étudiants de plus de 18 ans sont ajouté
            if (Period.between(student.getBirthDate(), LocalDate.now()).getYears() >= 18) {
                student.setTestCenterEntity(testCenterEntity);
                candidateRepository.save(student);
            }

        }
        return true;
    }
}
