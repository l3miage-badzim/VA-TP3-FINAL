package fr.uga.l3miage.spring.tp3.exceptions.rest;

import fr.uga.l3miage.spring.tp3.errors.ChangeSessionStatusErrorResponse;

public class ChangingStatusRestException extends RuntimeException{
    public ChangingStatusRestException(String message){
        super(message);
    }
}
