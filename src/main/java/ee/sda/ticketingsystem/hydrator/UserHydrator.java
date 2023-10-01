package ee.sda.ticketingsystem.hydrator;

import ee.sda.ticketingsystem.dto.UserDTO;
import ee.sda.ticketingsystem.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors
public class UserHydrator {

    public UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setUserType(user.getUserType());
        return dto;
    }

    public User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setUserId(user.getUserId());
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setUserType(user.getUserType());
        return user;
    }
}


