package inventarios.to.authorization;

/**
 *
 * @author Ruslan López Carro <scherzo16 at gmail.com>
 */
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserRole extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Description("El identificador de rol")
    @AdaptToScalar(Scalar.Int.class)
    private Long id;
    private String name;
    @ManyToMany
    private List<Permission> permissions;

    //recursive definition for hierarchichal quering
    @OneToMany
    private List<UserRole> subRoles;
}
