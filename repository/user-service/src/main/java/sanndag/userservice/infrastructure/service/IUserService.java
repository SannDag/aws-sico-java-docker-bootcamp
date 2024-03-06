package sanndag.userservice.infrastructure.service;

import sanndag.userservice.domain.dto.UserDTO;

public interface IUserService {

    public UserDTO getUserAndPosts(Long user_id);
}
