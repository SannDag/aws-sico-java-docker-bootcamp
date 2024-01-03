package sanndag.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanndag.backend.domain.dto.PersonDTO;
import sanndag.backend.domain.entities.PersonEntity;
import sanndag.backend.domain.mapper.PersonMapper;
import sanndag.backend.exception.BadRequestException;
import sanndag.backend.exception.ResourceNotFoundException;
import sanndag.backend.service.person.IPersonService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/persons")
@CrossOrigin("${allowed.origins}")
public class PersonController {

    private final IPersonService personService;
    private final PersonMapper personMapper;

    @PostMapping(("/save"))
    public ResponseEntity savePerson(@RequestBody PersonDTO dto){

            var dtoDNI = dto.getDni();

            if(dtoDNI == null || dtoDNI.isEmpty()){
                throw new BadRequestException("The DNI number cannot be empty.");
            }

            PersonEntity newPerson = personService.save(personMapper.dtoToEntity(dto));
            PersonDTO responseDto = personMapper.entityToDto(newPerson);

            return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Long id){
        var entity = personService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("person", "id", id));

        var dtoResponse = personMapper.entityToDto(entity);

        return ResponseEntity.ok(dtoResponse);
    }

    @GetMapping("/")
    public ResponseEntity<List<PersonDTO>> findAll(){
        List<PersonEntity> entityList = personService.findAll();

        if(entityList.isEmpty()){
            throw new ResourceNotFoundException("person list");
        }else{
            List<PersonDTO> dtoResponse = entityList.stream()
                    .map(personMapper::entityToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoResponse);
        }
    }

    @GetMapping("/all/{text}")
    public ResponseEntity<List<PersonDTO>> findAllBySimilarName(@PathVariable String text){
        List<PersonEntity> entityList = personService.findAllBySimilarName(text);

        if(entityList.isEmpty()){
            throw new ResourceNotFoundException("person list");
        }else{
            List<PersonDTO> dtoResponse = entityList.stream()
                    .map(personMapper::entityToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        var entity = personService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("person", "id", id));

        personService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody PersonDTO personDTO){
        var entity = personService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("person", "id", id));

        entity = personMapper.dtoToEntity(personDTO);

        personService.update(id, entity);

        return ResponseEntity.ok(personDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> update) {
        var entity = personService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("person", "id", id));

        personService.updateField(id, update);

        return ResponseEntity.ok().build();
    }

}
