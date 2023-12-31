package sanndag.backend.service.profession;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sanndag.backend.domain.entities.ProfessionEntity;
import sanndag.backend.exception.ResourceNotFoundException;
import sanndag.backend.repository.ProfessionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessionImpl implements IProfessionService {

    private final ProfessionRepository professionRepository;

    @Override
    public Optional<ProfessionEntity> findById(Long id) {
        return Optional.ofNullable(professionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("profession", "id", id)));
    }

    @Override
    public List<ProfessionEntity> findAll() {
        List<ProfessionEntity> entityList = new ArrayList<>(professionRepository.findAll());

        if (entityList.isEmpty()) {
            throw new ResourceNotFoundException("The list of professions is empty.");
        }

        return entityList;
    }

    @Override
    public List<ProfessionEntity> findAllBySimilarName(String text) {
        List<ProfessionEntity> entityList = new ArrayList<>(professionRepository.findBySimilarName(text));

        if (entityList.isEmpty()) {
            throw new ResourceNotFoundException("The list of professions is empty.");
        }

        return entityList;
    }

    @Override
    public void update(Long id, String name) {
        ProfessionEntity profession = professionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("profession","id", id));

        if (profession.getName() != null) profession.setName(name);

        professionRepository.save(profession);

    }

    @Override
    public ProfessionEntity updateEntity(ProfessionEntity entity) {
        return null;
    }
}
