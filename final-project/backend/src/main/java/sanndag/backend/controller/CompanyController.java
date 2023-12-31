package sanndag.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanndag.backend.domain.dto.CompanyDTO;
import sanndag.backend.domain.entities.CompanyEntity;
import sanndag.backend.domain.mapper.CompanyMapper;
import sanndag.backend.exception.ResourceNotFoundException;
import sanndag.backend.service.company.ICompanyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
@CrossOrigin({"http://localhost:8080", "http://localhost:4200"})
public class CompanyController {

    private final ICompanyService companyService;

    private final CompanyMapper companyMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable Long id){
        return companyService.findById(id)
                .map(entity -> ResponseEntity.ok(companyMapper.entityToDto(entity)))
                .orElseThrow(() -> new ResourceNotFoundException("company","id",id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompanyDTO>> findAll(){
        List<CompanyEntity> entityList = companyService.findAll();

        List<CompanyDTO> dtoResponse = entityList.stream()
                .map(companyMapper::entityToDto)
                .collect(Collectors.toList());

        if (!dtoResponse.isEmpty()) {
            return ResponseEntity.ok(dtoResponse);
        } else {
            throw new ResourceNotFoundException("company list");
        }
    }

    @GetMapping("/all/{text}")
    public ResponseEntity<List<CompanyDTO>> findAllBySimilarName(@PathVariable String text){
        List<CompanyEntity> entityList = companyService.findAllBySimilarName(text);

        if (entityList.isEmpty()) {
            throw new ResourceNotFoundException("company list");
        } else {

            List<CompanyDTO> dtoResponse = entityList.stream()
                    .map(companyMapper::entityToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoResponse);
        }

    }
}
