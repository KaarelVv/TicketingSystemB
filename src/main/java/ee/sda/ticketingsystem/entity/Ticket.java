package ee.sda.ticketingsystem.entity;

import ee.sda.ticketingsystem.enums.ticket.Priority;
import ee.sda.ticketingsystem.enums.ticket.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    @Column(length = 2000)
    private String description;
    private Date creationDate;
    private Priority priority;
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "ticket")
    private List<Attachment> attachment;

    @OneToMany(mappedBy = "ticket")
    private List<Comment> comment;

}