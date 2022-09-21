package inventarios.controller.strategy;

import inventarios.to.competition.CompetitorProduct;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Comparator;

@ApplicationScoped
public class CompetitionBasedPricing implements PricingStrategy {
//    @Inject
//    CompetitorProductService competitorProductService;

    @Override
    public double getMinimumPrice(String productName) {
//        return CompetitorProduct
//                .findAll()
//                .stream()
//                .filter(competitorProduct -> productName.equalsIgnoreCase(competitorProduct.getOurEquivalent().getName()))
//                .min(Comparator.comparing(CompetitorProduct::getPrice))
//                .map(CompetitorProduct::getPrice)
//                .orElse(0.0d);
        return 0d;
    }

    @Override
    public PricingStrategyName getStrategyName() {
        return PricingStrategyName.COMPETITION_BASED_PRICING;
    }
}
