package fr.uga.l3miage.spring.tp3.exceptions.handlers;

import fr.uga.l3miage.spring.tp3.exceptions.CandidatNotFoundResponse;
import fr.uga.l3miage.spring.tp3.exceptions.TestCenterOrStudentNotFoundException;
import fr.uga.l3miage.spring.tp3.exceptions.rest.CandidateNotFoundRestException;
import fr.uga.l3miage.spring.tp3.exceptions.rest.CreationSessionRestException;
import fr.uga.l3miage.spring.tp3.exceptions.rest.UpdateTestCenterRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class UpdateTestCenterExceptionHandler {


    @ExceptionHandler(UpdateTestCenterRestException.class)
    public ResponseEntity<TestCenterOrStudentNotFoundException> handle(HttpServletRequest httpServletRequest, Exception exception){
        UpdateTestCenterRestException updateTestCenterRestException = (UpdateTestCenterRestException) exception;
        TestCenterOrStudentNotFoundException response = TestCenterOrStudentNotFoundException
                .builder()
                .testCenterId(updateTestCenterRestException.getTestCenterId())
                .candidateId(updateTestCenterRestException.getCandidateId())
                .errorMessage(updateTestCenterRestException.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .build();
        return ResponseEntity.status(404).body(response);
    }
}
