package ee.sda.ticketingsystem.controller;

import ee.sda.ticketingsystem.entity.User;
import ee.sda.ticketingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    UserRepository userRepository;

    @PostMapping("/")
    public User createUser(@RequestBody User user){

        return userRepository.save(user);
    }

}
