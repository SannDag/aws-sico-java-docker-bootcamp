package sanndag.userservice.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sanndag.userservice.domain.model.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity,Long> {


}
