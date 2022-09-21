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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 *
 * @author Ruslan López Carro <scherzo16 at gmail.com>
 */
@AllArgsConstructor
@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductPrice  extends PanacheEntityBase {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @AdaptToScalar(Scalar.Int.class)
    private Long id;

    @OneToOne
    private Article article;

    int unitsSold;

    private Double price;

    //@Temporal(value = DATE)
    private Date stablished;
}
