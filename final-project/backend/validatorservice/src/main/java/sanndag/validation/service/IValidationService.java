package sanndag.validation.service;

public interface IValidationService {

    boolean dniValidation(String dni);

    boolean nameValidation(String name);

}
