package fr.uga.l3miage.spring.tp3.responses;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.Set;

public class CandidateResponse {


    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;

    private LocalDate birthDate;
    private boolean hasExtraTime;

}
