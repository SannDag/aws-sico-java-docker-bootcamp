package sanndag.persistence.service.person;

import sanndag.persistence.domain.entities.PersonEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IPersonService {

    PersonEntity save(PersonEntity person);
    Optional<PersonEntity> findById(Long id);
    Optional<PersonEntity> findByDni(String dni);
    List<PersonEntity> findAll();
    List<PersonEntity> findAllBySimilarName(String text);
    void delete(Long id);
    void update(Long id, PersonEntity person);
    PersonEntity updateField(Long id, Map<String, Object> fields);

}
