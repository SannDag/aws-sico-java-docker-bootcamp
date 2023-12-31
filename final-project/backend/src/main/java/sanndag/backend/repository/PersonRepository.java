package sanndag.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sanndag.backend.domain.entities.PersonEntity;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {

    @Query("SELECT p FROM PersonEntity p WHERE LOWER(p.name) LIKE %:text%")
    List<PersonEntity> findBySimilarName(@Param("text") String text);
}
