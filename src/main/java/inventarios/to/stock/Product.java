package inventarios.to.stock;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.graphql.api.AdaptToScalar;
import io.smallrye.graphql.api.Scalar;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.eclipse.microprofile.graphql.Description;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@AllArgsConstructor
@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Product  extends PanacheEntityBase {
  @Id
  @Column(name = "product_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Description("El identificador de producto")
  @AdaptToScalar(Scalar.Int.class)
  Long id;

  @OneToOne Article article;
  Long quantity;
}
