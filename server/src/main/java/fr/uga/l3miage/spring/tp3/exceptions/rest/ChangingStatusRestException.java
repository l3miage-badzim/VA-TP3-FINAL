package fr.uga.l3miage.spring.tp3.exceptions.rest;

public class ChangingStatusRestException extends RuntimeException{
    public ChangingStatusRestException(String message){
        super(message);
    }
}
