package fr.uga.l3miage.spring.tp3.controller;

import fr.uga.l3miage.spring.tp3.endpoints.TestCenterEndpoints;
import fr.uga.l3miage.spring.tp3.request.TestCenterAddStudentsRequest;
import fr.uga.l3miage.spring.tp3.services.TestCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class  TestCenterController implements TestCenterEndpoints {
    private final TestCenterService testCenterService;

    @Override
    public boolean addStudents(TestCenterAddStudentsRequest request) {
        return testCenterService.addStudents(request);
    }


}
