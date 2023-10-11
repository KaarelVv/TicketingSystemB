package ee.sda.ticketingsystem.utilities;

import ee.sda.ticketingsystem.dto.UserDTO;
import ee.sda.ticketingsystem.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityUtilities {

    public Optional<UserDTO> getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof CustomUserDetails) {
            User user = ((CustomUserDetails) principal).getUser();
            return Optional.of(new UserDTO()
                    .setId(user.getId())
                    .setName(user.getName())
                    .setUserType(user.getUserType())
                    .setEmail(user.getEmail())
                    .setRegisteredAt(user.getRegisteredAt()));
        }

        return Optional.empty();
    }

    public UserDTO getUser() {
        if(getLoggedInUser().isEmpty()) {
            throw new RuntimeException("401 UnAuthorized");
        }
        return getLoggedInUser().get();
    }
}
