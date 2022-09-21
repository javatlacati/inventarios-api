package inventarios.controller.strategy;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CostBasedPricing implements PricingStrategy {
  //    @Autowired
  //    private ProductService productService;
  @Override
  public double getMinimumPrice(String productName) {
    //        return productService.findAll()
    //                .stream()
    //                .filter(product -> productName.equalsIgnoreCase(product.getName()))
    //                .map(Product::getCharacteristics)
    //                .min(Comparator.comparing(ProductCharacteristic::getMonetaryValue))
    //                .map(ProductCharacteristic::getMonetaryValue)
    //                .orElse(0.0d);
    return 0d;
  }

  /**
   * Por ejemplo:
   *
   * <p>Preparar una hamburguesa con papas a la francesa, cuesta 30 pesos.
   *
   * <p>$30 / 0,3 = $100
   *
   * <p>En este caso, deberías cobrar 100 pesos. $30 serán para cubrir tus costos, y $70 serán tus
   * ganancias en bruto.
   *
   * @param productName
   * @return
   */
  public double getSuggestedPrice(String productName) {
    //        Suma los costos (incluyendo todos los gastos que incurren con tus proveedores) de
    // todos los ingredientes que requieres para preparar una porción de cada uno de tus platillos.
    //
    //        Para cada platillo, divide el costo total de sus ingredientes por el 30% o 0,3
    //
    //        El resultado es el precio final que deberías fijar para este platillo: 30% de tus
    // ganancias costearán los ingredientes, y el 70% serán tus ganancias en bruto.
    return 0d;
  }

  @Override
  public PricingStrategyName getStrategyName() {
    return PricingStrategyName.COST_BASED_PRICING;
  }
}
