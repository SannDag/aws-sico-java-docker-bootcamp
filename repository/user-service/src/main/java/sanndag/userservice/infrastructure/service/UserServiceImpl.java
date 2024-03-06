package sanndag.userservice.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sanndag.userservice.domain.dto.PostDTO;
import sanndag.userservice.domain.dto.UserDTO;
import sanndag.userservice.domain.model.UserEntity;
import sanndag.userservice.infrastructure.repository.IPostAPI;
import sanndag.userservice.infrastructure.repository.IUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService{

    private final IUserRepository userRepository;

    private final IPostAPI postAPI;

    @Override
    public UserDTO getUserAndPosts(Long user_id) {

        UserEntity user = userRepository.findById(user_id).orElse(null);

        List<PostDTO> postsList = postAPI.getPostsByUserId(user_id);

        UserDTO userDTO = new UserDTO(user.getUser_id(), user.getName(), user.getLastname(), user.getCellphone(), postsList);

        return userDTO;
    }
}
