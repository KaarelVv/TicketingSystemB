package ee.sda.ticketingsystem.service;

import ee.sda.ticketingsystem.entity.User;
import ee.sda.ticketingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Integer id){
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
