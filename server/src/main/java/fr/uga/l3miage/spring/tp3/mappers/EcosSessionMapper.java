package fr.uga.l3miage.spring.tp3.mappers;

import fr.uga.l3miage.spring.tp3.models.EcosSessionEntity;
import fr.uga.l3miage.spring.tp3.responses.EcosSessionResponse;
import org.mapstruct.Mapper;

@Mapper
public interface EcosSessionMapper {
    static EcosSessionResponse toResponse(EcosSessionEntity ecosSessionEntity);
}
