package sanndag.validation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanndag.validation.dto.ValidationDTO;
import sanndag.validation.service.IValidationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/validate")
@CrossOrigin({"http://localhost:8080", "http://localhost:4200"})
public class ValidationController {

    private final IValidationService validationService;

    @PostMapping("/name")
    public ResponseEntity<String> validateName(@RequestBody ValidationDTO validationDTO) {

        return validationService.nameValidation(validationDTO.getName())
                ? new ResponseEntity<>("{\"message\":\"Name is valid\"}", HttpStatus.OK)
                : new ResponseEntity<>("Invalid name. Try again.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/dni")
    public ResponseEntity<String> validateDNI(@RequestBody ValidationDTO validationDTO) {

        return validationService.dniValidation(validationDTO.getDni())
                ? new ResponseEntity<>("{\"message\":\"DNI is valid\"}", HttpStatus.OK)
                : new ResponseEntity<>("Invalid DNI. Try again.", HttpStatus.BAD_REQUEST);
    }




}
