package inventarios.to.infrastructure;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServingTable extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GraphQLQuery(name = "id", description = "El identificador de mesa")
    private Long id;

//    @GraphQLQuery(name = "name", description = "El nombre de la mesa")
    private String name;

    private int seats;
}
