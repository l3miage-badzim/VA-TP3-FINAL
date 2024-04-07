package fr.uga.l3miage.spring.tp3.exceptions.rest;

import lombok.Getter;

@Getter
public class UpdateTestCenterRestException extends RuntimeException{
    private final Long testCenterId;
    private final Long candidateId;
    public UpdateTestCenterRestException(String message, Long testCenterId, Long candidateId) {
        super(message);
        this.testCenterId = testCenterId;
        this.candidateId = candidateId;
    }
}
