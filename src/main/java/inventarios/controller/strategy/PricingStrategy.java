package inventarios.controller.strategy;

public interface PricingStrategy {
  double getMinimumPrice(String productName);

  PricingStrategyName getStrategyName();
}
