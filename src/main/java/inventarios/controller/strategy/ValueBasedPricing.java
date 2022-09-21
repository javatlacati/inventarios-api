package inventarios.controller.strategy;

import javax.enterprise.context.ApplicationScoped;

// tries to reflect how much a customer is willing to pay
// includes tangible and intangible benefits
@ApplicationScoped
public class ValueBasedPricing implements PricingStrategy {
    @Override
    public double getMinimumPrice(String productName) {
        return 0; //TODO some logic to see how in our business people react to price changes
    }

    @Override
    public PricingStrategyName getStrategyName() {
        return PricingStrategyName.VALUE_BASED_PRICING;
    }
}
