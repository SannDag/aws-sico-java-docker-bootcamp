package sanndag.postsservice.infrastructure.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sanndag.postsservice.domain.PostEntity;
import sanndag.postsservice.domain.dto.PostDTO;
import sanndag.postsservice.infrastructure.repository.IPostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService{

    private final IPostRepository postRepository;

    @Override
    @CircuitBreaker(name="user-service", fallbackMethod = "fallbackGetPostsByUserId")
    @Retry(name="user-service")
    public List<PostEntity> getPostsByUserId(Long userId) {
        createException();

        PostDTO postDTO = new PostDTO();
        postDTO.setUser_id(userId);
        postDTO.setTitle("asdad ");

        return postRepository.findPostByUserId(userId);

    }

    public PostDTO fallbackGetPostsByUserId( Throwable throwable){
        return new PostDTO(99999999L, "Failed",null);
    }

    public void createException(){
        throw new IllegalArgumentException("Resilience and Circuit Braker test");
    }


}
