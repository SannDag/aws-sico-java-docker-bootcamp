package sanndag.persistence.service.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sanndag.persistence.domain.entities.CompanyEntity;
import sanndag.persistence.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService{

    private final CompanyRepository companyRepository;

    @Override
    public Optional<CompanyEntity> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public List<CompanyEntity> findAll() {
        List<CompanyEntity> entityList = new ArrayList<>(companyRepository.findAll());

//        if (entityList.isEmpty()) {
//            throw new EntityNotFoundException("The list of companies is empty.");
//        }

        return entityList;
    }

    @Override
    public Optional<CompanyEntity> findByName(String name) {
        return companyRepository.findByName(name);
    }

    @Override
    public List<CompanyEntity> findAllBySimilarName(String text) {
        List<CompanyEntity> entityList = new ArrayList<>(companyRepository.findBySimilarName(text));

//        if (entityList.isEmpty()) {
//            throw new EntityNotFoundException("The list of companies is empty.");
//        }

        return entityList;
    }
}
