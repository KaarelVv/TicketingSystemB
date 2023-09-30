package ee.sda.ticketingsystem.controller;

import ee.sda.ticketingsystem.entity.User;

import ee.sda.ticketingsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @GetMapping
    public List<User> listAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping()
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }


}
