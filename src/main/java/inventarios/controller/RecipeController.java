package inventarios.controller;

import inventarios.service.RecipeService;
import inventarios.service.model.Periodicity;
import inventarios.to.recipes.Recipe;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import javax.inject.Inject;
import java.util.List;

@GraphQLApi
public class RecipeController {
  @Inject RecipeService recipeService;

  @Query
  public Uni<List<Recipe>> getRecipeList() {
    return recipeService.getRecipeList();
  }

  @Query
  public Uni<List<Recipe>> getStarRecipes(Periodicity periodicity) {
    return recipeService.getStarRecipes(periodicity);
  }
}
