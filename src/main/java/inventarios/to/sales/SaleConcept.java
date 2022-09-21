package inventarios.to.sales;

import inventarios.to.recipes.Recipe;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.graphql.api.AdaptToScalar;
import io.smallrye.graphql.api.Scalar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.graphql.Description;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleConcept extends PanacheEntityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Description("El identificador del concepto de venta")
  @AdaptToScalar(Scalar.Int.class)
  private Long id;

  @OneToOne
  //    @GraphQLQuery(name = "producto", description = "Producto parte del concepto de venta")
  private Recipe product;

  //    @GraphQLQuery(name = "cantidad", description = "Piezas compradas")
  private Integer quantity;
}
