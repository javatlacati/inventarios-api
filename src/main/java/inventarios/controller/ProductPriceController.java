package inventarios.controller;

import inventarios.controller.strategy.PricingStrategyFactory;
import inventarios.service.RecipeService;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.inject.Inject;

@GraphQLApi
public class ProductPriceController {
  @Inject PricingStrategyFactory pricingStrategyFactory;

  @Inject
  RecipeService recipeService;

  // TODO save product price

  @Query
  @Description("calculates the minimum price based on the specified strategy")
  public double estimateMinimumPrice(
      @Parameter(
              examples = {
                @ExampleObject(value = "COST_BASED_PRICING"),
                @ExampleObject(value = "COMPETITION_BASED_PRICING"),
                @ExampleObject(value = "VALUE_BASED_PRICING")
              })
          @DefaultValue("COMPETITION_BASED_PRICING")
          String strategyName,
      String productName) {
    return pricingStrategyFactory.findByName(strategyName).getMinimumPrice(productName);
  }


  @Query
  public Uni<Double> getGrossProfit() {
      return recipeService.getGrossProfit();
  }
}
