package fr.uga.l3miage.spring.tp3.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class ExaminerResponse {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;

    private String login;
    private String password;
}
