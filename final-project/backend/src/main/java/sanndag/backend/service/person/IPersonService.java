package sanndag.backend.service.person;

import sanndag.backend.domain.entities.PersonEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IPersonService {

    PersonEntity save(PersonEntity person);
    Optional<PersonEntity> findById(Long id);
    List<PersonEntity> findAll();
    List<PersonEntity> findAllBySimilarName(String text);
    void delete(Long id);
    void update(Long id, PersonEntity person);
    void partialUpdate(Long id, Map<String, Object> updates);

}
