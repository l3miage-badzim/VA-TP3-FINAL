package fr.uga.l3miage.spring.tp3.services;

import fr.uga.l3miage.spring.tp3.components.CandidateComponent;
import fr.uga.l3miage.spring.tp3.components.TestCenterComponent;
import fr.uga.l3miage.spring.tp3.exceptions.rest.CreationSessionRestException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.TestCenterNotFoundException;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestCenterService {
    private final CandidateComponent candidateComponent;
    private final TestCenterComponent testCenterComponent;

    public boolean addStudentColletionInTestCenter(Long idTestCenter, List<Long> listIds) {
        List<CandidateEntity> candidateEntityList = candidateComponent.getCandidatsByIds(listIds);
        try {
            TestCenterEntity testCenterEntity = testCenterComponent.getTestCenterById(idTestCenter);
            return testCenterComponent.addStudentColletionInTestCenter(testCenterEntity, candidateEntityList);
        }
        catch (TestCenterNotFoundException e) {
            throw new CreationSessionRestException(e.getMessage());
        }

    }


}