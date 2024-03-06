package sanndag.persistence.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanndag.persistence.domain.dto.PersonDTO;
import sanndag.persistence.domain.entities.PersonEntity;
import sanndag.persistence.domain.mapper.PersonMapper;
import sanndag.persistence.exception.BadRequestException;
import sanndag.persistence.exception.ResourceNotFoundException;
import sanndag.persistence.service.person.IPersonService;

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



    @Operation(summary = "Save new person",
                description = "First, validate that the DNI is not null or empty")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operation successful, resource created", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Person not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
            })
    @PostMapping(("/save"))
    public ResponseEntity savePerson(@RequestBody PersonDTO dto){

            var dtoDNI = dto.getDni();

            if(dtoDNI == null || dtoDNI.isEmpty()){
                throw new BadRequestException("The DNI number cannot be empty.");
            }

            PersonEntity newPerson = personService.save(personMapper.dtoToEntity(dto));
            PersonDTO responseDto = personMapper.entityToDto(newPerson);

            return new ResponseEntity (responseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Get person by ID",
            description = "Find a person by ID and throw an exception if it was not found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Person not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Long id){
        var entity = personService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("person", "id", id));

        var dtoResponse = personMapper.entityToDto(entity);

        return ResponseEntity.ok(dtoResponse);
    }

    @Operation(summary = "Get all persons",
            description = "Find a list of persons and throw an exception if the list is empty.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Persons not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
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

    @Operation(summary = "Get all persons by similar letter on name",
            description = "Retrieve a list of persons whose names match the specified letter(s) provided by the user. Throw an exception if the resulting list is empty.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Persons not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
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

    @Operation(summary = "Delete person by ID",
            description = "Delete a person with the specified ID provided by the user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operation successful, but no content is available to display", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Person not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        var entity = personService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("person", "id", id));

        personService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update person by ID",
            description = "Update the fields of a person with the specified ID provided by the user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Person not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody PersonDTO personDTO){
        var entity = personService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("person", "id", id));

        entity = personMapper.dtoToEntity(personDTO);

        personService.update(id, entity);

        return new ResponseEntity (personDTO, HttpStatus.OK);
    }

    @Operation(summary = "Patch person by ID",
            description = "Partially update the fields of a person with the specified ID provided by the user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Person not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/{id}")
    public ResponseEntity partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> update) {
        var entity = personService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("person", "id", id));

        personService.updateField(id, update);

        return ResponseEntity.ok().build();
    }

}
