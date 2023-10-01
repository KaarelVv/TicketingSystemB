package ee.sda.ticketingsystem.entity;

import ee.sda.ticketingsystem.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;
    private String title;
    private LocalDateTime creationDate;
    private String priority;
    private String category;
    private Status status;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "ticket")
    private List<Attachment> attachment;
    @OneToMany(mappedBy = "ticket")
    private List<Comment> comment;

}