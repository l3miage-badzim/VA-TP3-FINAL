package fr.uga.l3miage.spring.tp3.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Builder
public class TestCenterAddStudentsRequest {
    private Long testCenterId;
    private List<Long> studentIds;
}
