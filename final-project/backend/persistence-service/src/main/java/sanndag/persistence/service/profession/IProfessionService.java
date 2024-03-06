package sanndag.persistence.service.profession;

import sanndag.persistence.domain.entities.ProfessionEntity;

import java.util.List;
import java.util.Optional;

public interface IProfessionService {

    Optional<ProfessionEntity> findById(Long id);
    List<ProfessionEntity> findAll();
    List<ProfessionEntity> findAllBySimilarName(String text);
    void update(Long id, String name);
    ProfessionEntity updateEntity(ProfessionEntity entity);
}
