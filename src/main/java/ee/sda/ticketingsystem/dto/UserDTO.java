package ee.sda.ticketingsystem.dto;


import ee.sda.ticketingsystem.enums.UserType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserDTO {

    private Integer userId;
    private String name;
    private String email;
    private String password;
    private UserType userType;
    private Date registeredAt;

}
