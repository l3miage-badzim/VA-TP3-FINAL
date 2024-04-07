package fr.uga.l3miage.spring.tp3.exceptions.rest;

import fr.uga.l3miage.spring.tp3.enums.SessionStatus;
import lombok.Getter;

@Getter
public class SessionConflitRestException extends RuntimeException{
    private final SessionStatus actualStatus;

    public SessionConflitRestException(String message, SessionStatus actualStatus){
        super(message);
        this.actualStatus = actualStatus;
    }
}
