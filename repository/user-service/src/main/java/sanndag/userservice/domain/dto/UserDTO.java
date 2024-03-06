package sanndag.userservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long user_id;
    private String name;
    private String lastname;
    private String cellphone;
    private List<PostDTO> postsList;
}
