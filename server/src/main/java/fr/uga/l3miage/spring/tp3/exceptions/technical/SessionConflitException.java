package fr.uga.l3miage.spring.tp3.exceptions.technical;

import fr.uga.l3miage.spring.tp3.enums.SessionStatus;
import lombok.Getter;

@Getter
public class SessionConflitException extends Exception{
    private final SessionStatus actualStatus;
    public SessionConflitException(String format, SessionStatus actualStatus){
        super(format);
        this.actualStatus = actualStatus;

    }
}
