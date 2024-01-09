package sanndag.backend.domain.mapper;

import org.springframework.stereotype.Component;
import sanndag.backend.domain.dto.ProfessionDTO;
import sanndag.backend.domain.entities.ProfessionEntity;

@Component
public class ProfessionMapper {

    public ProfessionDTO entityToDto(ProfessionEntity professionEntity){
        return ProfessionDTO.builder()
                .name(professionEntity.getName())
                .build();
    }

    public ProfessionEntity dtoToEntity(ProfessionDTO professionDTO){
        return ProfessionEntity.builder()
                .name(professionDTO.getName())
                .build();

    }
}
