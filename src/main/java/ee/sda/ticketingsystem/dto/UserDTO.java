package ee.sda.ticketingsystem.dto;


import ee.sda.ticketingsystem.enums.user.UserType;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserDTO {


    private Integer id;

    @NotEmpty(message = "Name is required.")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters.")
    private String name;

    @Email(message = "Invalid email format.")
    @NotEmpty(message = "Email is required.")
    private String email;

    @NotEmpty(message = "Password is required.")
    @Size(min = 4, message = "Password should be at least 4 characters long.")

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain at least one uppercase letter and one number.")
    private String password;

    @NotNull(message = "User type is required.")
    private UserType userType;

    @PastOrPresent(message = "The registration date should be in the past or present.")
    private Date registeredAt;

}

//    (?=.*[A-Z]): This checks if there's at least one uppercase letter somewhere in the string.
//
//    (?=.*\\d): This checks if there's at least one digit somewhere in the string.
//
//    .+: This ensures that the password has at least one character.

