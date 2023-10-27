package ee.sda.ticketingsystem.dto.response;

import ee.sda.ticketingsystem.enums.user.UserType;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserResponseDTO {

    private Integer id;

    @NotEmpty(message = "Name is required.")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters.")
    private String name;

    @Email(message = "Invalid email format.")
    @NotEmpty(message = "Email is required.")
    private String email;

    @NotNull(message = "User type is required.")
    private UserType userType;



}
