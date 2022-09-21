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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Article
 * @author francisco
 */
@AllArgsConstructor
@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Article extends PanacheEntityBase {
  @Id
  @Column(name = "product_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Description("El identificador de producto")
  @AdaptToScalar(Scalar.Int.class)
  private Long id;

  @NotNull(message = "la fecha de peticion no puede estar vacia")
  private Date dateRequest;

  private Date dateSupplied;

  private Date dateDepleted;

  @NotEmpty(message = "el nombre de producto no puede estar vacio")
  private String name;

  @NotNull private Integer quantity;
  /**
   * nivel de stock.
   * Es la cantidad de existencias de un artículo almacenadas en un momento dado.
   */
  @NotNull private Integer quantityAvailable;

  private Double unitAcquisitionPrice;

  @NotEmpty(message = "debe de tener numero de serie")
  private String serial;

  @OneToOne private ProductCharacteristic characteristics;
}
