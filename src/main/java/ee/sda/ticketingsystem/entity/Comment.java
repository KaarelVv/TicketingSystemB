package ee.sda.ticketingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;
    private Date commentDate;
    private Date lastUpdated;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public Comment copy() {
        return new Comment()
                .setId(this.id)
                .setContent(this.content)
                .setCommentDate(this.commentDate)
                .setLastUpdated(this.lastUpdated);
    }

}
