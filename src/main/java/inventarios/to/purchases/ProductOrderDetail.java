package inventarios.to.purchases;

import inventarios.to.authorization.LoginUser;
import inventarios.to.stock.Article;
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
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductOrderDetail extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @AdaptToScalar(Scalar.Int.class)
    private Long id;
    @OneToMany
    private List<Article> articles;
    @OneToOne
    private LoginUser employee;
    private Date orderDate;
//    String localNumber;
//    String entity;
//    String places;
}
