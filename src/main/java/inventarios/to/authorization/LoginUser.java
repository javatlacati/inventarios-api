package inventarios.to.authorization;

import inventarios.to.personnel.EmployeeDetail;
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
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.FetchType.LAZY;

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
public class LoginUser extends PanacheEntityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Description("El identificador de usuario")
  @AdaptToScalar(Scalar.Int.class)
  private Long id;

  //   @GraphQLQuery(name = "name", description = "El nombre de usuario")
  @NotEmpty(message = "{username.notempty}")
  private String userName;

  @NotEmpty(message = "{password.notempty}")
  private String password;

  @OneToOne(optional = true//, fetch = LAZY
  )
  private EmployeeDetail employeeDetail;

  private boolean active;

  public LoginUser(String userName, String password) {
    this.userName = userName;
    this.password = password;
    active = true;
  }
}
