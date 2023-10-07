package ee.sda.ticketingsystem.service;

import ee.sda.ticketingsystem.component.UserDetailServiceImp;
import ee.sda.ticketingsystem.dto.UserDTO;
import ee.sda.ticketingsystem.entity.User;
import ee.sda.ticketingsystem.exception.InvalidPasswordException;
import ee.sda.ticketingsystem.exception.UserNotFoundException;
import ee.sda.ticketingsystem.hydrator.UserHydrator;
import ee.sda.ticketingsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {


    private UserRepository userRepository;
    private UserHydrator userHydrator;
    private UserDetailServiceImp userDetailServiceImp;
    private PasswordEncoder passwordEncoder;



    public UserDTO getUserById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return userHydrator.convertToDTO(user);
        }
        throw new UserNotFoundException("User not found with id: " + id);
    }

    public List<UserDTO> getAllUsers() {
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
                .setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(user);

        return userHydrator.convertToDTO(savedUser);
    }

    public UserDTO loginUser(UserDTO userDTO) throws Exception {
        User user = userHydrator.convertToEntity(userDTO)
                .setEmail(userDTO.getEmail())
                .setPassword(userDTO.getPassword());

        UserDetails userDetails = userDetailServiceImp.loadUserByUsername(user.getEmail());

        if (userDetails != null) {

            if (userDetails.getPassword().equals(user.getPassword())) {
                User userEntity = userDetailServiceImp.findByUsername(user.getEmail());
                return userHydrator.convertToDTO(userEntity);
            } else {
                throw new InvalidPasswordException("Wrong Password!");
            }
        } else {
            throw new UserNotFoundException("User not found !");
        }
    }
}





