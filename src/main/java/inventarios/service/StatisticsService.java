package inventarios.service;

import inventarios.to.infrastructure.StorageLocation;
import inventarios.to.infrastructure.StorageLocationCost;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;

@ApplicationScoped
public class StatisticsService {

  @Inject StorageLocationService storageLocationService;

  public Uni<DoubleSummaryStatistics> storageCostStatistics() {
    return storageLocationService
        .findAllStorageLocations()
        .map(Collection::stream)
        .map(
            storageLocationStream ->
                storageLocationStream
                    .map(StorageLocation::getCost)
                    .mapToDouble(StorageLocationCost::getMonthlyCost)
                    .summaryStatistics());
  }

//  Tiempo para preparación de pedidos
//  Es el tiempo empleado y necesario para la confección de un encargo. Para poder determinar el rendimiento general de un sistema de manutención, respectivamente, de un sistema de almacenado, hay que calcular el valor del tiempo promedio empleado por posición.

  //  public double usedInventory(Date lastInventoryDate) {
  //    // Inventario Inicial + Compras – Inventario final = Inventario Utilizado
  //    double inventarioInicial =
  //            productService.findAll().stream()
  //                    .filter(product -> product.getDateOut() == null)
  //                    .filter(product -> product.getDateIn().before(lastInventoryDate))
  //                    .mapToDouble(
  //                            product ->
  //                                    product.getCharacteristics().getMonetaryValue()
  //                                            * product.getQuantityAvailable())
  //                    .sum();
  //    double compras =
  //            purchaseService.findAll().stream()
  //                    .filter(purchase -> purchase.getDate() != null)
  //                    .filter(purchase -> purchase.getDate().after(lastInventoryDate))
  //                    .map(Purchase::getProducto)
  //                    .flatMapToDouble(
  //                            (List<Product> productList) ->
  //                                    productList.stream()
  //                                            .mapToDouble(
  //                                                    product ->
  //
  // product.getCharacteristics().getMonetaryValue()
  //                                                                    *
  // product.getQuantityAvailable()))
  //                    .sum();
  //    double inventarioFinal =
  //            productService.findAll().stream()
  //                    .filter(product -> product.getDateOut() == null)
  //                    .filter(product -> product.getDateIn().after(lastInventoryDate))
  //                    .mapToDouble(
  //                            product ->
  //                                    product.getCharacteristics().getMonetaryValue()
  //                                            * product.getQuantityAvailable())
  //                    .sum();
  //    return (inventarioInicial + compras) - inventarioFinal;
  //  }
}
