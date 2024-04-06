package fr.uga.l3miage.spring.tp3.components;

import fr.uga.l3miage.spring.tp3.exceptions.technical.TestCenterNotFoundException;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import fr.uga.l3miage.spring.tp3.repositories.TestCenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestCenterComponent {
    private final TestCenterRepository testCenterRepository;

    TestCenterEntity getTestCenterById(Long id) throws TestCenterNotFoundException {
        return testCenterRepository.findById(id).orElseThrow(() -> new TestCenterNotFoundException("testCenter non trouvé", id));
    }
}
