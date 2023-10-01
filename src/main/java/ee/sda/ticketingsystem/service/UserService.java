package ee.sda.ticketingsystem.service;

import ee.sda.ticketingsystem.dto.UserDTO;
import ee.sda.ticketingsystem.entity.User;
import ee.sda.ticketingsystem.exception.UserNotFoundException;
import ee.sda.ticketingsystem.hydrator.UserHydrator;
import ee.sda.ticketingsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    UserRepository userRepository;
    UserHydrator userHydrator;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){

        return userRepository.save(user);
    }

    public UserDTO getUserById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            return userHydrator.convertToDTO(user);
        }
        throw new UserNotFoundException("User not found with id: " + id);
    }

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userHydrator::convertToDTO)
                .collect(Collectors.toList());
    }

@Transactional
    public UserDTO createUser(UserDTO userDto) {
    User user = userHydrator.convertToEntity(userDto)
            .setUserId(userDto.getUserId())
            .setEmail(userDto.getEmail())
            .setName(userDto.getName())
            .setUserType(userDto.getUserType())
            .setPassword(userDto.getPassword());
    User savedUser = userRepository.save(user);

    return userHydrator.convertToDTO(savedUser);
}


    }





