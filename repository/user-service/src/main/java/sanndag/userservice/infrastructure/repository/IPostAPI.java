package sanndag.userservice.infrastructure.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sanndag.userservice.domain.dto.PostDTO;

import java.util.List;

@FeignClient(name="posts-service")
public interface IPostAPI {

    @GetMapping("/api/v1/posts/{user_id}")
    List<PostDTO> getPostsByUserId(@PathVariable("user_id") Long user_id) ;
}
