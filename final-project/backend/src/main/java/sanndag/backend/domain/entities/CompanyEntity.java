package sanndag.backend.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

}

/*
TechSolutions Innovate
Quantum Dynamics Systems
Cybernetic Innovations Inc.
* */
