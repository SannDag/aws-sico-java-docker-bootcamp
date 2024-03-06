package sanndag.persistence.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanndag.persistence.domain.dto.CompanyDTO;
import sanndag.persistence.domain.entities.CompanyEntity;
import sanndag.persistence.domain.mapper.CompanyMapper;
import sanndag.persistence.exception.ResourceNotFoundException;
import sanndag.persistence.service.company.ICompanyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
@CrossOrigin("${allowed.origins}")
public class CompanyController {

    private final ICompanyService companyService;

    private final CompanyMapper companyMapper;

    @Operation(summary = "Get company by ID",
            description = "Try to find a company by ID; if it is not found, throw an exception.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable Long id){
        return companyService.findById(id)
                .map(entity -> ResponseEntity.ok(companyMapper.entityToDto(entity)))
                .orElseThrow(() -> new ResourceNotFoundException("company","id",id));
    }

    @Operation(summary = "Get list of companies",
            description = "Try to find a list of companies; if none are found, throw an exception.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Companies not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/")
    public ResponseEntity<List<CompanyDTO>> findAll(){
        List<CompanyEntity> entityList = companyService.findAll();

        if (entityList.isEmpty()) {
            throw new ResourceNotFoundException("company list");
        } else {

            List<CompanyDTO> dtoResponse = entityList.stream()
                    .map(companyMapper::entityToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtoResponse);
        }
    }

    @Operation(summary = "Get list of companies",
            description = "Try to find a list of companies; if none are found, throw an exception.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Companies not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
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
