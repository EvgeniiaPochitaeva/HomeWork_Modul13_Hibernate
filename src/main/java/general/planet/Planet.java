package general.planet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor

@Data
@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @Column (name = "id", length = 5)
    private String id;

    @Column (name = "name", length = 500)
    private String name;
}