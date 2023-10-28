package ee.sda.ticketingsystem.entity;


import ee.sda.ticketingsystem.enums.user.UserType;
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
@Table(name = "account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //, orphanRemoval = true
    private List<Ticket> ticket;
    private Date registeredAt;

}
