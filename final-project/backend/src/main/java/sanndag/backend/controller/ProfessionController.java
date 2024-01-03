package sanndag.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanndag.backend.domain.dto.ProfessionDTO;
import sanndag.backend.domain.entities.ProfessionEntity;
import sanndag.backend.domain.mapper.ProfessionMapper;
import sanndag.backend.exception.ResourceNotFoundException;
import sanndag.backend.service.profession.IProfessionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/professions")
@CrossOrigin({"http://localhost:8080", "http://localhost:4200"})
public class ProfessionController {

    private final IProfessionService professionService;

    private final ProfessionMapper professionMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionDTO> getCompany(@PathVariable Long id){
        var entity = professionService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The company with id " + id + " was not found."));

        var dtoResponse = professionMapper.entityToDto(entity);

        return ResponseEntity.ok(dtoResponse);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProfessionDTO>> findAll(){
        List<ProfessionEntity> entityList = professionService.findAll();

        List<ProfessionDTO> dtoResponse = entityList.stream()
                .map(professionMapper::entityToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoResponse);
    }

    @GetMapping("/all/{text}")
    public ResponseEntity<List<ProfessionDTO>> findAllBySimilarName(@PathVariable String text){
        List<ProfessionEntity> entityList = professionService.findAllBySimilarName(text);

        List<ProfessionDTO> dtoResponse = entityList.stream()
                .map(professionMapper::entityToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoResponse);

    }
}
