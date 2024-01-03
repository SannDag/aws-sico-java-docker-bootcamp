package sanndag.backend.service.person;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.util.ReflectionUtils;
import sanndag.backend.domain.entities.PersonEntity;
import sanndag.backend.exception.ResourceAlreadyExistsException;
import sanndag.backend.exception.ResourceNotFoundException;
import sanndag.backend.repository.PersonRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements IPersonService {

    private final PersonRepository personRepository;

    @Override
    public PersonEntity save(PersonEntity person) {
        var personDNI = person.getDni();

        var existingPerson = personRepository.findByDni(personDNI);

        if (!existingPerson.isEmpty()) {
            throw new ResourceAlreadyExistsException("person", "dni", personDNI);
        }

        return personRepository.save(person);
    }

    @Override
    public Optional<PersonEntity> findById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public Optional<PersonEntity> findByDni(String dni) {

        var existingPerson = personRepository.findByDni(dni);

        if (existingPerson.isEmpty()) {
            throw new ResourceNotFoundException("person", "dni", dni);
        }

        return personRepository.findByDni(dni);
    }

    @Override
    public List<PersonEntity> findAll() {
        List<PersonEntity> entityList = new ArrayList<>(personRepository.findAll());

        if (entityList.isEmpty()) {
            throw new ResourceNotFoundException("The list of persons is empty.");
        }

        return entityList;
    }

    @Override
    public List<PersonEntity> findAllBySimilarName(String text) {
        List<PersonEntity> entityList = new ArrayList<>(personRepository.findBySimilarName(text));

        if (entityList.isEmpty()) {
            throw new ResourceNotFoundException("The list of persons is empty.");
        }

        return entityList;
    }

    @Override
    public void delete(Long id) {

        personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("person", "id", id));

        personRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void update(Long id, PersonEntity person) {

        var existingPerson = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("person", "id", id));

        if (person.getName() != null && !person.getName().isEmpty()) existingPerson.setName(person.getName());
        if (person.getLastname() != null && !person.getLastname().isEmpty()) existingPerson.setLastname(person.getLastname());
        if (person.getDni() != null && !person.getDni().isEmpty()) existingPerson.setDni(person.getDni());
        if (person.getAddress() != null && !person.getAddress().isEmpty()) existingPerson.setAddress(person.getAddress());
        if (person.getCity() != null && !person.getCity().isEmpty()) existingPerson.setCity(person.getCity());
        if (person.getState() != null && !person.getState().isEmpty()) existingPerson.setState(person.getState());
        if (person.getCountry() != null && !person.getCountry().isEmpty()) existingPerson.setCountry(person.getCountry());
        if (person.getProfessionEntity() != null && !person.getProfessionEntity().getName().isEmpty()) existingPerson.setProfessionEntity(person.getProfessionEntity());
        if (person.getCompanyEntity() != null && !person.getCompanyEntity().getName().isEmpty()) existingPerson.setCompanyEntity(person.getCompanyEntity());

        personRepository.save(existingPerson);
    }

    @Transactional
    @Override
    public PersonEntity updateField(Long id, Map<String, Object> fields) {

        Optional<PersonEntity> existingPerson = personRepository.findById(id);

        if (existingPerson.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(PersonEntity.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, existingPerson.get(), value);
                } else {
                    throw new IllegalArgumentException("Invalid field: " + key);
                }
            });

            return personRepository.save(existingPerson.get());

        } else {
            throw new ResourceNotFoundException("person", "id", id);
        }
    }
}



