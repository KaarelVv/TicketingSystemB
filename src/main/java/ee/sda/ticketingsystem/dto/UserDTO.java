package ee.sda.ticketingsystem.dto;


import lombok.Data;

@Data
public class UserDTO {

    private Integer userId;
    private String name;
    private String email;
    private String password;

}
