package sanndag.validation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanndag.validation.dto.ValidationDTO;
import sanndag.validation.service.IValidationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/validate")
@CrossOrigin("${allowed.origins}")
public class ValidationController {

    private final IValidationService validationService;

    @Operation(summary = "Name validation.",
            description = "Validates a name provided by the user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid name", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/name")
    public ResponseEntity<String> validateName(@RequestBody ValidationDTO validationDTO) {

        return validationService.nameValidation(validationDTO.getName())
                ? new ResponseEntity<>("{\"message\":\"Name is valid\"}", HttpStatus.OK)
                : new ResponseEntity<>("Invalid name. Try again.", HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "DNI validation.",
            description = "Validates a DNI provided by the user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Ok", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid DNI", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/dni")
    public ResponseEntity<String> validateDNI(@RequestBody ValidationDTO validationDTO) {

        return validationService.dniValidation(validationDTO.getDni())
                ? new ResponseEntity<>("{\"message\":\"DNI is valid\"}", HttpStatus.OK)
                : new ResponseEntity<>("Invalid DNI. Try again.", HttpStatus.BAD_REQUEST);
    }




}
