package inventarios.to.sales;

import inventarios.to.authorization.LoginUser;
import inventarios.to.infrastructure.ServingTable;
import inventarios.to.recipes.Recipe;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.graphql.api.AdaptToScalar;
import io.smallrye.graphql.api.Scalar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.graphql.Description;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder extends PanacheEntityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Description("El identificador de la comanda")
  @AdaptToScalar(Scalar.Int.class)
  private Long id;

  /**
   * Productos contenidos en esta cuenta. Note que no todos los productos deben de cobrarse, algunos
   * podrían haber salido defectuosos pero se incluyen ya que de todos modos nos permiten conocer
   * que gastos generan.
   */
  //    @GraphQLQuery(name = "conceptos", description = "Conceptos incluidos en la nota de venta")
  @OneToMany(cascade = CascadeType.ALL)
  private List<SaleConcept> conceptos;

  //    @GraphQLQuery(name = "promociones", description = "Promociones incluidos en la nota de
  // venta")
  @OneToMany(cascade = CascadeType.ALL)
  private List<Offer> offers;

  /** Mesero que atendió a la mesa */
  //    @GraphQLQuery(name = "mesero", description = "Mesero al que se le pagó la comanda")
  @OneToOne private LoginUser mesero;

  /** Fecha y hora de creación de comanda */
  @Temporal(javax.persistence.TemporalType.DATE)
  //    @GraphQLQuery(name = "fecha", description = "Fecha en que se registra la comanda")
  private Date fecha;

  //    @GraphQLQuery(name = "mesa", description = "Mesa en que se registra la comanda")
  @OneToOne private ServingTable servingTable;

  /** Personas atendidas en esta cuenta */
  //    @GraphQLQuery(name = "personas", description = "Numero de personas atendidas")
  private Integer people;

  /** Monto parcial o totalmente cobrado */
  //    @GraphQLQuery(name = "montoCobrado", description = "Dinero a pagar o pagado")
  private Double amountCharged;

  /** Permite saber si esta cuenta ya se liquidó */
  //    @GraphQLQuery(name = "pagado", description = "Indica si esta cuenta ya se pagó")
  private Boolean fullyPaid;

  // TODO mover estos métodos a un servicio

  /**
   * Obtiene el monto total a cobrar en esta cuenta
   *
   * @return
   */
  public Double calcularTotalACobrar() {
    final Date today = new Date();
    return conceptos.stream()
        .map(SaleConcept::getProduct)
        .filter((producto) -> !producto.isDefective())
        .mapToDouble(Recipe::getSalePrice)
        .sum();
  }

  public List<Recipe> getDefectiveProducts() {
    return conceptos.stream()
        .map(SaleConcept::getProduct)
        .filter(Recipe::isDefective)
        .collect(Collectors.toList());
  }
}
