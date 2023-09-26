package ee.sda.ticketingsystem.controller;

import ee.sda.ticketingsystem.entity.User;
import ee.sda.ticketingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    UserRepository userRepository;

    @PostMapping("/")
    public User createUser(@RequestBody User user){

        return userRepository.save(user);
    }

    @GetMapping
    public List<User> listAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id){
        return userRepository.findById(id).get();
    }





}
