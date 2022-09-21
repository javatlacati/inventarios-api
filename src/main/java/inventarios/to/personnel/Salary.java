package inventarios.to.personnel;

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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Salary extends PanacheEntityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Description("El identificador de este sueldo")
  @AdaptToScalar(Scalar.Int.class)
  private Long id;

  /** Semanal, diario, mensual, quincenal, etc */
  //    @GraphQLQuery(name = "periodicidad", description = "La periodicidad con que se debe de
  // pagar")
  private String periodicidad;

  //    @GraphQLQuery(name = "monto", description = "La cantidad que se debe de pagar")
  private Double monto;

  //    @GraphQLQuery(name = "fechaInicio", description = "Fecha en que se empieza a pagar")
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date fechaInicio;

  //    @GraphQLQuery(name = "fechaFin", description = "Fecha en que se termina de pagar")
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date fechaFin;
}
