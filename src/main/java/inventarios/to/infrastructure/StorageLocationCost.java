package inventarios.to.infrastructure;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.graphql.api.AdaptToScalar;
import io.smallrye.graphql.api.Scalar;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class StorageLocationCost extends PanacheEntityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @AdaptToScalar(Scalar.Int.class)
  private Long id;

  private double cost;
  private double monthParity;

  public double getMonthlyCost() {
    return cost / monthParity;
  }
}
