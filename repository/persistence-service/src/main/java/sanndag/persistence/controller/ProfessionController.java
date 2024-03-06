package sanndag.persistence.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanndag.persistence.domain.dto.ProfessionDTO;
import sanndag.persistence.domain.entities.ProfessionEntity;
import sanndag.persistence.domain.mapper.ProfessionMapper;
import sanndag.persistence.exception.ResourceNotFoundException;
import sanndag.persistence.service.profession.IProfessionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/professions")
@CrossOrigin("${allowed.origins}")
public class ProfessionController {

    private final IProfessionService professionService;

    private final ProfessionMapper professionMapper;

    @Operation(summary = "Get profession by ID.",
            description = "Try to find a profession by ID; if it is not found, throw an exception.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Profession not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionDTO> getProfession(@PathVariable Long id){

        return professionService.findById(id)
                .map(entity -> ResponseEntity.ok(professionMapper.entityToDto(entity)))
                .orElseThrow(() -> new ResourceNotFoundException("profession","id",id));

    }

    @Operation(summary = "Get list of professions by ID.",
            description = "Try to find a list of professions by ID; if none are found, throw an exception.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Professions not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/")
    public ResponseEntity<List<ProfessionDTO>> findAll(){
        List<ProfessionEntity> entityList = professionService.findAll();

        if(entityList.isEmpty()){
            throw new ResourceNotFoundException("profession list");
        } else {

            List<ProfessionDTO> dtoResponse = entityList.stream()
                    .map(professionMapper::entityToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtoResponse);
        }


    }

    @Operation(summary = "Get list of professions by ID.",
            description = "Try to find a list of professions by ID; if none are found, throw an exception.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Professions not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/all/{text}")
    public ResponseEntity<List<ProfessionDTO>> findAllBySimilarName(@PathVariable String text){
        List<ProfessionEntity> entityList = professionService.findAllBySimilarName(text);

        if(entityList.isEmpty()){
            throw new ResourceNotFoundException("profession list");
        } else {

            List<ProfessionDTO> dtoResponse = entityList.stream()
                    .map(professionMapper::entityToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtoResponse);
        }

    }
}
