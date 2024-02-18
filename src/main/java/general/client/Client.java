package general.client;

import general.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "name", length = 200)
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}

