package inventarios.to.stock;

import inventarios.to.authorization.LoginUser;
import inventarios.to.infrastructure.StorageLocation;
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
public class ProductCharacteristic  extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @AdaptToScalar(Scalar.Int.class)
    private Long id;

    @OneToOne
    private LoginUser encargadoregistro;
    @OneToOne
    private StorageLocation location;

    private String shape;
    private String color;
    private String size;
    //    String cosas;
//    String variedad;
//    String descripcion;
//    String precio;
//    String moneda;
    private String quality;
//    String vistobueno;
//    String cosa;
//    String bueno;
//    String malo;
//    String regular;
//    String sabor;
//    String creado;
//    String ensamblado;
//    String facturado;
//    String compra;
//    String venta;
}
