package sanndag.postsservice.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanndag.postsservice.domain.PostEntity;
import sanndag.postsservice.infrastructure.service.IPostService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostsController {

    private final IPostService postService;

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/{user_id}")
    public List<PostEntity> getPostsByUserId (@PathVariable Long user_id) {
        System.out.println("-----Puerto: " + serverPort);
        return postService.getPostsByUserId(user_id);
    }


}
