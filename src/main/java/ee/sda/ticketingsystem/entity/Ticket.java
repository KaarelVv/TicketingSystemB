package ee.sda.ticketingsystem.entity;

import ee.sda.ticketingsystem.enums.ticket.Priority;
import ee.sda.ticketingsystem.enums.ticket.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
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
    private Date lastUpdated;
    private Priority priority;
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "ticket")
    private List<HistoryLog> historyLog;

    public Ticket copy() {
        Ticket copiedTicket = new Ticket()
        .setId(this.id)
        .setTitle(this.title)
        .setDescription(this.description)
        .setCreationDate(this.creationDate)
        .setPriority(this.priority)
        .setStatus(this.status)
        .setUser(this.user);

        if (this.comments != null) {
            copiedTicket.comments = new ArrayList<>();
            for (Comment comment : this.comments) {
                copiedTicket.comments.add(comment.copy());
            }
        }
        return copiedTicket;
    }



}