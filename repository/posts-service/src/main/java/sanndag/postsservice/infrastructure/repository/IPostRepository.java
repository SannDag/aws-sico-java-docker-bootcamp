package sanndag.postsservice.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sanndag.postsservice.domain.PostEntity;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<PostEntity,Long> {

    @Query("SELECT p FROM PostEntity p WHERE p.user_id = :user_id")
    List<PostEntity> findPostByUserId(@Param("user_id")Long user_id);
}
