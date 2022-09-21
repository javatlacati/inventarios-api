package inventarios.controller.strategy;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ApplicationScoped
public class PricingStrategyFactory {

  private Map<String, PricingStrategy> strategies;

  @Inject CostBasedPricing costBasedPricing;
  @Inject CompetitionBasedPricing competitionBasedPricing;
  @Inject ValueBasedPricing valueBasedPricing;

  @Inject
  public PricingStrategyFactory() {
    createStrategy(Set.of(costBasedPricing, competitionBasedPricing, valueBasedPricing));
  }

  private void createStrategy(Set<PricingStrategy> strategySet) {
    strategies = new HashMap<>();
    strategySet.forEach(
        pricingStrategy ->
            strategies.put(pricingStrategy.getStrategyName().getName(), pricingStrategy));
  }

  public PricingStrategy findByName(String name) {
    return strategies.get(name);
  }
}
