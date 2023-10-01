package ee.sda.ticketingsystem.repository;

import ee.sda.ticketingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
