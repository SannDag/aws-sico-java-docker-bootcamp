package sanndag.backend.service.company;

import sanndag.backend.domain.entities.CompanyEntity;

import java.util.List;
import java.util.Optional;

public interface ICompanyService {

    Optional<CompanyEntity> findById(Long id);
    List<CompanyEntity> findAll();
    Optional<CompanyEntity> findByName(String name);

    List<CompanyEntity> findAllBySimilarName(String text);
}
