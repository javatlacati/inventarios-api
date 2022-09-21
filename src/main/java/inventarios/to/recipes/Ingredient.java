package inventarios.to.recipes;

import inventarios.to.stock.Article;
import inventarios.to.stock.Product;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.graphql.api.AdaptToScalar;
import io.smallrye.graphql.api.Scalar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @AdaptToScalar(Scalar.Int.class)
    private Long id;

//    @NonNull
    @OneToMany//(cascade = CascadeType.ALL)
    private List<Product> productos;

//    private Double quantity;

//    private String units;

    String description;
}
