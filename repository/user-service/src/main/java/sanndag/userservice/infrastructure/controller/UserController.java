package sanndag.userservice.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanndag.userservice.domain.dto.UserDTO;
import sanndag.userservice.infrastructure.service.IUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userService;

    @GetMapping("/posts/{user_id}")
    public UserDTO getUserAndPosts (@PathVariable Long user_id){

        UserDTO user = userService.getUserAndPosts(user_id);

        return null;
    }

}
