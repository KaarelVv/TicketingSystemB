package ee.sda.ticketingsystem.service;

import ee.sda.ticketingsystem.dto.UserDto;
import ee.sda.ticketingsystem.entity.User;
import ee.sda.ticketingsystem.exception.UserNotFoundException;
import ee.sda.ticketingsystem.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public UserDto createUser(UserDto userDto) {
     
        User user = new User()
                .setUserId(userDto.getUserId())
                .setEmail(userDto.getEmail())
                .setName(userDto.getName())
                .setUserType(userDto.getUserType())
                .setPassword(userDto.getPassword());

        return userRepository.save(user);

    }
}
    




