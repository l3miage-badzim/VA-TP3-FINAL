package fr.uga.l3miage.spring.tp3.controller;

import fr.uga.l3miage.spring.tp3.endpoints.TestCenterEndpoints;
import fr.uga.l3miage.spring.tp3.services.TestCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestCenterController implements TestCenterEndpoints {
    private final TestCenterService testCenterService;

    @Override
    public boolean addStudentColletionToTestCenter(Long testCenterId, List<Long> studentCollectionIds) {
        return testCenterService.addStudentColletionToTestCenter(testCenterId, studentCollectionIds);
    }


}
