package fr.uga.l3miage.spring.tp3.services;

import fr.uga.l3miage.spring.tp3.components.CandidateComponent;
import fr.uga.l3miage.spring.tp3.components.TestCenterComponent;
import fr.uga.l3miage.spring.tp3.exceptions.rest.UpdateTestCenterRestException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.CandidateNotFoundException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.TestCenterNotFoundException;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import fr.uga.l3miage.spring.tp3.request.TestCenterAddStudentsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TestCenterService {
    private final CandidateComponent candidateComponent;
    private final TestCenterComponent testCenterComponent;

    public boolean addStudents(TestCenterAddStudentsRequest testCenterUpdateRequest) {
        try {
            TestCenterEntity testCenterEntity = testCenterComponent.getTestCenterById(testCenterUpdateRequest.getTestCenterId());
            Set<CandidateEntity> candidateEntityList = new HashSet<>(candidateComponent.getCandidatsByIds(testCenterUpdateRequest.getStudentIds())) ;
            return testCenterComponent.addStudents(testCenterEntity, candidateEntityList);
        }
        catch (TestCenterNotFoundException | CandidateNotFoundException e) {
            if (e instanceof  TestCenterNotFoundException) throw new UpdateTestCenterRestException(e.getMessage(), ((TestCenterNotFoundException) e).getTestCenterId(), null);
            throw new UpdateTestCenterRestException(e.getMessage(), testCenterUpdateRequest.getTestCenterId(), ((CandidateNotFoundException) e).getCandidateId());
        }

    }


}
