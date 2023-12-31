package sanndag.backend.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProfessionDTO {
    Long id;
    String name;
}
