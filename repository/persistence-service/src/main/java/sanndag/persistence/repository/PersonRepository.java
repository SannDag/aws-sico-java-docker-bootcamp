package sanndag.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sanndag.persistence.domain.entities.PersonEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {

    @Query("SELECT p FROM PersonEntity p WHERE LOWER(p.name) LIKE %:text%")
    List<PersonEntity> findBySimilarName(@Param("text") String text);

    @Query("SELECT p FROM PersonEntity p WHERE p.dni = :dni")
    Optional<PersonEntity> findByDni(@Param("dni") String dni);
}
