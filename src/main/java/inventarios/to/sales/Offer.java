package inventarios.to.sales;

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

/**
 * Esta clase es para promociones que no sean un descuento en productos, es decir, por ejemplo 5
 * chicas una botella o 5% de descuento en cuenta por cliente frecuente, etc.
 *
 * @author ruslan.lopez
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer extends PanacheEntityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Description("El identificador de la promo")
  @AdaptToScalar(Scalar.Int.class)
  private Long id;

  //    @GraphQLQuery(name = "nombre", description = "El nombre de la promo")
  private String name;

  //    @GraphQLQuery(name = "descripcion", description = "Descripción humanamente legible de la
  // promo")
  private String description;

  //    @GraphQLQuery(name = "regla", description = "Regla en código para activar la promo")
  private String rule;

  //    @GraphQLQuery(name = "activa", description = "indica si esta promo está activa")
  private boolean active;
}
