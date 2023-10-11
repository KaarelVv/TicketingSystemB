package ee.sda.ticketingsystem.service;

import ee.sda.ticketingsystem.dto.UserDTO;
import ee.sda.ticketingsystem.entity.User;
import ee.sda.ticketingsystem.enums.user.UserType;
import ee.sda.ticketingsystem.exception.UserNotFoundException;
import ee.sda.ticketingsystem.hydrator.UserHydrator;
import ee.sda.ticketingsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserHydrator userHydrator;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO createUser(UserDTO userDto) {

        User user = userHydrator.convertToEntity(userDto)
                .setId(userDto.getId())
                .setEmail(userDto.getEmail())
                .setName(userDto.getName())
                .setUserType(UserType.CUSTOMER)
                .setPassword(passwordEncoder.encode(userDto.getPassword()))
                .setRegisteredAt(new Date());
        System.out.println("User created!");
        User savedUser = userRepository.save(user);

        return userHydrator.convertToDTO(savedUser);
    }
    public UserDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id:" + id + " not found"));
            return userHydrator.convertToDTO(user);
        }



    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userHydrator::convertToDTO)
                .collect(Collectors.toList());
    }



}





