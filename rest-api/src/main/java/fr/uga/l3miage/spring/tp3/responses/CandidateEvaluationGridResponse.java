package fr.uga.l3miage.spring.tp3.responses;

import java.time.LocalDateTime;
import java.util.Set;

public class CandidateEvaluationGridResponse {

    private Long sheetNumber;

    private double grade;

    private LocalDateTime submissionDate;

    private CandidateResponse candidateEntity;

    private ExaminerResponse examinerEntity;

    private ExamResponse examEntity;


}
