package inventarios.service;

import inventarios.to.stock.Cellar;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class StockService {
  //    la cantidad o valor en dolares de los productos que un restaurante tiene en la casa.
  public Uni<Double> getSittingInventory() {
    Multi<Cellar> cellars = Cellar.<Cellar>streamAll();
    return cellars
        .map(Cellar::getIngredientesEnExistencia)
        .map(
            products ->
                products.stream()
                    .mapToDouble(
                        product ->
                            product.getQuantityAvailable() * product.getUnitAcquisitionPrice())
                    .sum())
        .collect()
        .with(Collectors.reducing(Double::sum))
        .map(aDouble -> aDouble.orElse(0d));
  }

  //  Agotamiento-la cantidad o el valor en dolares del inventario que se utiliza en un periodo de
  // tiempo establecido, como semanal, mensual o diario.

  //  Uso-la cantidad fisica o valor en dolares del inventario sentado dividido por el agotamiento
  // promedio en un periodo determinado.

  //  Desviacion-la diferencia entre el coste de un articulo de inventario y el coste del importe de
  // uso.
  //  Por ejemplo, si un restaurante tiene un valor de 500 dolares en carne y al final del dia
  // vendio filete por valor de 450 dolares, la diferencia seria de $50. Esto significa que hay 50
  // dolares en carne que no se tiene en cuenta.

  //punto de pedido
//  Momento en el cual es necesario hacer un nuevo pedido para reaprovisionar el almacén dado el volumen de stock.

//  Período medio de maduración
//  Es el tiempo que transcurre desde que se hace la inversión (en materias primas, productos semielaborados o artículos terminados) hasta que estos se venden y se cobran.

//  Stock medio
//  Volumen medio de existencias que tenemos en el almacén durante un período de tiempo. Expresa la inversión en existencias que, por término medio, realiza la empresa.


}
