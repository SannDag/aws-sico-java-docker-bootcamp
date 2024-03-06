package sanndag.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sanndag.persistence.domain.entities.ProfessionEntity;

import java.util.List;

@Repository
public interface ProfessionRepository extends JpaRepository <ProfessionEntity, Long> {

    @Query("SELECT p FROM ProfessionEntity p WHERE LOWER(p.name) LIKE %:text%")
    List<ProfessionEntity> findBySimilarName(@Param("text") String text);
}
