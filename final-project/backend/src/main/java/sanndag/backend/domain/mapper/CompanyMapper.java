package sanndag.backend.domain.mapper;

import org.springframework.stereotype.Component;
import sanndag.backend.domain.dto.CompanyDTO;
import sanndag.backend.domain.entities.CompanyEntity;

@Component
public class CompanyMapper {

    public CompanyDTO entityToDto(CompanyEntity companyEntity){
        return CompanyDTO.builder()
                .id(companyEntity.getId())
                .name(companyEntity.getName())
                .build();
    }

    public CompanyEntity dtoToEntity(CompanyDTO companyDTO){
        return CompanyEntity.builder()
                .id(companyDTO.getId())
                .name(companyDTO.getName())
                .build();

    }
}
