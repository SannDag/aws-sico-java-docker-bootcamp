package sanndag.backend.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profession")
public class ProfessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profession_id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
}

/*
Software Developer
Data Scientist
Systems Analyst
 */