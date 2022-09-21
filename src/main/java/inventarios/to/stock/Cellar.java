package inventarios.to.stock;

import inventarios.to.infrastructure.StorageLocation;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cellar extends PanacheEntityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Description("El identificador de bodega")
  @AdaptToScalar(Scalar.Int.class)
  private Long id;

  //    @GraphQLQuery(name = "name", description = "El nombre de la bodega")
  private String name;

  @OneToOne StorageLocation storageLocation;

  //    @GraphQLQuery(name = "ingredientesEnExistencia", description = "Los insumos en la bodega")
    @OneToMany private List<Article> ingredientesEnExistencia;

  //    @GraphQLQuery(name = "enseresEnExistencia", description = "Los enseres de la bodega")
    @OneToMany private List<Chattel> enseresEnExistencia;
}
