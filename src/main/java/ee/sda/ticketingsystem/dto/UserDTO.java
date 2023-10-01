package ee.sda.ticketingsystem.dto;

import ee.sda.ticketingsystem.component.UserType;
import lombok.Data;

@Data
public class UserDTO {

    private Integer userId;
    private String name;
    private String email;
    private String password;
    private UserType userType;

}
