package ee.sda.ticketingsystem.dto;


import ee.sda.ticketingsystem.enums.user.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserDTO {

    private Integer id;
    private String name;
    @Email
    @NotNull
    @NotEmpty
    private String email;
    @UniqueElements
    @Size(min = 4)
    private String password;
    private UserType userType;
    private Date registeredAt;

}
