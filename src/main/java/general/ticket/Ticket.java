package general.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import general.planet.Planet;
import general.client.Client;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor

@Data
@Entity
public class Ticket {
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int ticketId;

    @Column (name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne (cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToOne()
    @JoinColumn(name = "from_planet_id", referencedColumnName = "id")
    private Planet planetFrom;

    @OneToOne
    @JoinColumn(name = "to_planet_id", referencedColumnName = "id")
    private Planet planetTo;
}
