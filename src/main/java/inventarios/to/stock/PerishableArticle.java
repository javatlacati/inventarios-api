package inventarios.to.stock;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.graphql.Description;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class PerishableArticle extends Article {
  @Description("Fecha en que caduca el insumo")
  private Date fechaDeCaducidad;

  public boolean isExpired() {
    return fechaDeCaducidad.before(new Date());
  }
}
