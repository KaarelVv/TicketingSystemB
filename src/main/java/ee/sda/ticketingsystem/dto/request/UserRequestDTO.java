package ee.sda.ticketingsystem.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRequestDTO {
    private Integer id;
    private String email;
    private String password;
}
