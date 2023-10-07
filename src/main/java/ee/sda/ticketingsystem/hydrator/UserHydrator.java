package ee.sda.ticketingsystem.hydrator;

import ee.sda.ticketingsystem.dto.UserDTO;
import ee.sda.ticketingsystem.entity.User;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Component
@Accessors(chain = true)
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
        user.setUserId(dto.getUserId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setUserType(dto.getUserType());
        user.setRegisteredAt(dto.getRegisteredAt());
        return user;
    }
}


