package ee.sda.ticketingsystem.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserResponseDTO {

    private Integer id;
    private String name;
    private String email;

}
