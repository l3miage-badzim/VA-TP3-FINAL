package fr.uga.l3miage.spring.tp3.exceptions.handlers;

import fr.uga.l3miage.spring.tp3.enums.SessionStatus;
import fr.uga.l3miage.spring.tp3.exceptions.SessionConflitErrorResponse;
import fr.uga.l3miage.spring.tp3.exceptions.rest.SessionConflitRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class SessionConflitExceptionHandler {


    @ExceptionHandler(SessionConflitRestException.class)
    public ResponseEntity<SessionConflitErrorResponse> handle(HttpServletRequest httpServletRequest, Exception exception){
        SessionConflitRestException SessionConflitRestException = (SessionConflitRestException) exception;
        fr.uga.l3miage.spring.tp3.responses.enums.SessionStatus actualStatus = SessionConflitRestException.getActualStatus() == null ? null : mapSessionStatus(SessionConflitRestException.getActualStatus());
        SessionConflitErrorResponse response = SessionConflitErrorResponse
                .builder()
                .uri(httpServletRequest.getRequestURI())
                .errorMessage(SessionConflitRestException.getMessage())
                .actualStatus(actualStatus)
                .build();

        return ResponseEntity.status(409).body(response);
    }

    public static fr.uga.l3miage.spring.tp3.responses.enums.SessionStatus mapSessionStatus(
            fr.uga.l3miage.spring.tp3.enums.SessionStatus serverStatus) {
        return fr.uga.l3miage.spring.tp3.responses.enums.SessionStatus.valueOf(serverStatus.name());
    }
}
