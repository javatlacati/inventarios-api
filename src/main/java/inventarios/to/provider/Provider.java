package inventarios.to.provider;

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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author EfraJiJim
 */
@AllArgsConstructor
@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Provider extends PanacheEntityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @AdaptToScalar(Scalar.Int.class)
  private Long id;

  @NotEmpty(message = "El nobre de proveedor no debe estar vacio")
  @Description("The provider name")
  private String name;

  private String address;
  private String number;

  @Digits(integer = 10, fraction = 0, message = "el numero de telefono debe ser a 10 digitos")
  private String telephoneNumber;

  @Email
  @Description("The provider email")
  private String email;

  @Digits(integer = 5, fraction = 0, message = "el codigo postal debe tener 5 digitos")
  private String poBox;

  //    String lugar;
  //    String domicilio;
  //    String fecha;
  //    String addres1;
  //    String comunidad;
  //    String state;
  //    String rfc;
  //    String data;
  //    String comunidades;

  //productos??
}
