package fr.uga.l3miage.spring.tp3.services;

import fr.uga.l3miage.spring.tp3.components.CandidateComponent;
import fr.uga.l3miage.spring.tp3.components.TestCenterComponent;
import fr.uga.l3miage.spring.tp3.exceptions.rest.CreationSessionRestException;
import fr.uga.l3miage.spring.tp3.exceptions.rest.UpdateTestSessionRestException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.CandidateNotFoundException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.TestCenterNotFoundException;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TestCenterService {
    private final CandidateComponent candidateComponent;
    private final TestCenterComponent testCenterComponent;

    public boolean addStudentColletionToTestCenter(Long idTestCenter, List<Long> listIds) {
        try {
            TestCenterEntity testCenterEntity = testCenterComponent.getTestCenterById(idTestCenter);
            Set<CandidateEntity> candidateEntityList = new HashSet<>(candidateComponent.getCandidatsByIds(listIds)) ;
            return testCenterComponent.addStudentColletionToTestCenter(testCenterEntity, candidateEntityList);
        }
        catch (TestCenterNotFoundException | CandidateNotFoundException e) {
            throw new UpdateTestSessionRestException(e.getMessage());
        }

    }


}
