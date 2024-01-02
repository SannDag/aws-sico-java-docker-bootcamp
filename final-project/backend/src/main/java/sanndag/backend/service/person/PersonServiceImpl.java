package sanndag.backend.service.person;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import sanndag.backend.domain.entities.PersonEntity;
import sanndag.backend.domain.entities.ProfessionEntity;
import sanndag.backend.domain.mapper.CompanyMapper;
import sanndag.backend.exception.ResourceAlreadyExistsException;
import sanndag.backend.exception.ResourceNotFoundException;
import sanndag.backend.repository.PersonRepository;
import sanndag.backend.service.company.ICompanyService;
import sanndag.backend.service.profession.IProfessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements IPersonService {

    private final PersonRepository personRepository;
    private final IProfessionService professionService;
    private final ICompanyService companyService;
    private final CompanyMapper companyMapper;

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
    public void partialUpdate(Long id, Map<String, Object> updates) {

        var existingPerson = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("person", "id", id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existingPerson.setName((String) value);
                    break;
                case "lastname":
                    existingPerson.setLastname((String) value);
                    break;
                case "dni":
                    existingPerson.setDni((String) value);
                    break;
                case "address":
                    existingPerson.setAddress((String) value);
                    break;
                case "city":
                    existingPerson.setCity((String) value);
                    break;
                case "state":
                    existingPerson.setState((String) value);
                    break;
                case "country":
                    existingPerson.setCountry((String) value);
                    break;

                case "profession":

                    if (value instanceof Map<?, ?> professionInfo) {
                        Object professionName = professionInfo.get("name");
                        Object professionId = professionInfo.get("id");

                        if (professionName instanceof String && professionId instanceof Long) {
                            Long profId = (Long) professionId;
                            String newName = (String) professionName;

                            ProfessionEntity existingProfession = professionService.findById(profId)
                                    .orElseThrow(() -> new ResourceNotFoundException("Profession with ID " + profId + " not found"));

                            existingProfession.setName(newName);

                            ProfessionEntity updatedProfession = professionService.updateEntity(existingProfession);

                        }

                        //todo

//                        ProfessionEntity professionEntity = professionService.findById(Long.parseLong((String) value))
//                                .orElseThrow(() -> new EntityNotFoundException("Profession with ID " + value + " not found."));
//                        existingPerson.setProfession(professionEntity);
                    }
//                case "companyEntity":
//                    //no estoy pudiendo hacer que funcione está actualiación
//                    if (value instanceof Map) {
//                        Map<String, Object> companyUpdate = (Map<String, Object>) value;
//                        if (companyUpdate.containsKey("name")) {
//                            String companyName = (String) companyUpdate.get("name");
//
//                            companyService.findByName(companyName)
//                                    .ifPresent(existingPerson::setCompanyEntity);
//
//                        }
//                    }
//                    break;

                default:
                    break;
            }
        });

        personRepository.save(existingPerson);
    }

}



