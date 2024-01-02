package sanndag.backend.domain.mapper;

import org.springframework.stereotype.Component;
import sanndag.backend.domain.dto.PersonDTO;
import sanndag.backend.domain.entities.PersonEntity;

@Component
public class PersonMapper {
    public PersonDTO entityToDto(PersonEntity person){
        return PersonDTO.builder()
                .name(person.getName())
                .lastname(person.getLastname())
                .dni(person.getDni())
                .address(person.getAddress())
                .city(person.getCity())
                .state(person.getState())
                .country(person.getCountry())
                .profession(person.getProfessionEntity())
                .companyEntity(person.getCompanyEntity())
                .build();
    }

    public PersonEntity dtoToEntity(PersonDTO personDto){
        return PersonEntity.builder()
                .name(personDto.getName())
                .lastname(personDto.getLastname())
                .dni(personDto.getDni())
                .address(personDto.getAddress())
                .city(personDto.getCity())
                .state(personDto.getState())
                .country(personDto.getCountry())
                .professionEntity(personDto.getProfession())
                .companyEntity(personDto.getCompanyEntity())
                .build();

    }
}
