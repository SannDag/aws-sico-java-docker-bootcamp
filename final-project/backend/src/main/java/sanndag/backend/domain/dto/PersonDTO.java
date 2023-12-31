package sanndag.backend.domain.dto;

import lombok.Builder;
import lombok.Data;
import sanndag.backend.domain.entities.CompanyEntity;
import sanndag.backend.domain.entities.ProfessionEntity;

@Builder
@Data
public class PersonDTO {
    Long id;
    String name;
    String lastname;
    String dni;
    String address;
    String city;
    String state;
    String country;
    ProfessionEntity profession;
    CompanyEntity companyEntity;
}
