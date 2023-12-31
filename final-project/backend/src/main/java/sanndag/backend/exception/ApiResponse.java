package sanndag.backend.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    String incorrectRequest;
    String details;
}
