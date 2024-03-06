package sanndag.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sanndag.persistence.domain.entities.CompanyEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity,Long> {

    @Query("SELECT c FROM CompanyEntity c WHERE LOWER(c.name) LIKE %:text%")
    Optional<CompanyEntity> findByName(@Param("text") String text);

    @Query("SELECT c FROM CompanyEntity c WHERE LOWER(c.name) LIKE %:text%")
    List<CompanyEntity> findBySimilarName(@Param("text") String text);
}
