package ee.sda.ticketingsystem.dto;

import ee.sda.ticketingsystem.component.UserType;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {


    private Integer userId;
    private String name;
    private String email;
    private String password;
    private UserType userType;


}
