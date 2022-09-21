package inventarios.to.authorization;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * @author Ruslan López Carro <scherzo16 at gmail.com>
 */
@AllArgsConstructor
@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LoginUserHasRole extends PanacheEntityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @AdaptToScalar(Scalar.Int.class)
  private Long id;

  @OneToOne private LoginUser user;
  @OneToMany private List<UserRole> roles;
}
