package fr.uga.l3miage.spring.tp3.components;

import fr.uga.l3miage.spring.tp3.exceptions.technical.TestCenterNotFoundException;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import fr.uga.l3miage.spring.tp3.repositories.TestCenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TestCenterComponent {
    private final TestCenterRepository testCenterRepository;

    public TestCenterEntity getTestCenterById(Long id) throws TestCenterNotFoundException {
        return testCenterRepository.findById(id).orElseThrow(() -> new TestCenterNotFoundException("testCenter non trouvé", id));
    }

    public boolean addStudentColletionInTestCenter(TestCenterEntity testCenterEntity, List<CandidateEntity> listStudents) throws TestCenterNotFoundException{
        Set<CandidateEntity> candidateEntities = testCenterEntity.getCandidateEntities();
        boolean result = candidateEntities.addAll(listStudents);
        testCenterEntity.setCandidateEntities(candidateEntities);
        testCenterRepository.save(testCenterEntity);
        return result;
    }
}
