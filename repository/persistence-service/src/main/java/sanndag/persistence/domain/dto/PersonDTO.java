package sanndag.persistence.domain.dto;

import lombok.Builder;
import lombok.Data;
import sanndag.persistence.domain.entities.CompanyEntity;
import sanndag.persistence.domain.entities.ProfessionEntity;

@Builder
@Data
public class PersonDTO {

    String name;
    String lastname;
    String dni;
    String address;
    String city;
    String state;
    String country;
    ProfessionEntity profession;
    CompanyEntity company;
}
