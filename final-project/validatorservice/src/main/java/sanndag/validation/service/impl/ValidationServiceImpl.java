package sanndag.validation.service.impl;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import sanndag.validation.service.IValidationService;

@Service
public class ValidationServiceImpl implements IValidationService {

    @Override
    public boolean nameValidation(String name) {
        validateNotEmpty(name, "The name cannot be empty.");
        validateNameFormat(name, "Invalid name. Try again.");
        return true;
    }

    @Override
    public boolean dniValidation(String dni) {
        validateNotEmpty(dni, "DNI number must contain 8 characters.");
        validateDNIFormat(dni, "Invalid DNI. Try again.");
        return true;
    }

    private void validateNotEmpty(String value, String errorMessage) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateNameFormat(String name, String errorMessage) {
        if (!isValidName(name)) {
            throw new ValidationException(errorMessage);
        }
    }

    private void validateDNIFormat(String dni, String errorMessage) {
        if (!isValidDNI(dni)) {
            throw new ValidationException(errorMessage);
        }
    }

    private boolean isValidDNI(String dni) {
        // Basic validation, must contain 8 numbers.
        return dni.matches("\\d{8}");
    }

    private boolean isValidName(String name) {
        // Verifying that name is not empty and only contains letters
        return !name.trim().isEmpty() && name.matches("^[a-zA-Z]+$");
    }
}
