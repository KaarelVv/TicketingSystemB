package ee.sda.ticketingsystem.entity;


import ee.sda.ticketingsystem.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "profile")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String name;
    private String email;
    private String password;
    private UserType userType;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> ticket;
    private Date registeredAt;

}
