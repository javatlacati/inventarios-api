package inventarios.to.recipes;

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
import javax.persistence.OneToOne;
import java.util.List;

/**
 * El platillo
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe extends PanacheEntityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @AdaptToScalar(Scalar.Int.class)
  Long id;

  private String name;
  private String category;
  private boolean isDefective;
  private String defectiveCause;
  // unit selling price
  private double salePrice;
  @OneToMany List<Ingredient> ingredients;
  @OneToOne private RecipePreparationDetails preparationDetails;
}
