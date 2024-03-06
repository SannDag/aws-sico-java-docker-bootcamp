package sanndag.postsservice.infrastructure.service;

import sanndag.postsservice.domain.PostEntity;

import java.util.List;

public interface IPostService {
    List<PostEntity> getPostsByUserId(Long userId);
}
