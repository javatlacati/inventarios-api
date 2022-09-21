package inventarios.to.stock;

import inventarios.to.provider.Provider;
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
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chattel extends PanacheEntityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Description("El identificador de enser")
  @AdaptToScalar(Scalar.Int.class)
  private Long id;

  /** Nombre del artículo. */
  //    @GraphQLQuery(name = "name", description = "El nombre del enser")
  private String name;

  private Date dateIn;
  private Date dateOut;

  /** Tienda o forma en la que se obtuvo. */
  @OneToOne
  //    @GraphQLQuery(name = "procedencia", description = "Proveedor del que procede")
  private Provider provenance;
  // TODO analizar estado de enser
  //    @GraphQLQuery(name = "unidad", description = "La unidad  en que se mide el insumo")
  private String unit;
  @NotNull
  private Integer quantity;
}
