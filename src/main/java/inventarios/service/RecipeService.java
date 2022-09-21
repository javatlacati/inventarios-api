package inventarios.service;

import inventarios.service.model.Periodicity;
import inventarios.to.recipes.Ingredient;
import inventarios.to.recipes.Recipe;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class RecipeService {
  public Uni<List<Recipe>> getRecipeList() {
    //    Recipe.find("id")//.<Product>filter("getProduct").stream()
    //        .<Recipe>filter((Recipe recipe)->{recipe.getIngredients().stream().flatMap(ingredient
    // -> ingredient.)});
    // TODO
    return Recipe.listAll();
  }

  //  Platillos estrella: Lucrativos y populares: Estos son los platillos que quieres mantener en tu
  // menú.
  //  Trata de entender a qué se debe el éxito de estos platillos, y cuando introduzcas nuevas
  // recetas a tu menú, ¡intenta reproducirlo!
  public Uni<List<Recipe>> getStarRecipes(Periodicity periodicity) {
    return Recipe.listAll();
  }
  //  Platillos base: Poco lucrativos, pero populares.
  //  No le quites a tus comensales los platillos que más anhelan. Si un platillo vende bien, deja
  // que permanezca en tu menú, aunque no te salga rentable prepararlo. Intenta analizar a qué se
  // debe su alto costo: tu objetivo será reducir el costo de este platillo en el futuro próximo,
  // pero lo más importante es darle siempre a tus clientes lo que quieren.
  //  Platillos rompecabezas: Lucrativos, pero no muy populares.
  //  No vale la pena eliminar un platillo de tu menú si te está generando ganancias. Pero si no
  // tienes buen volumen de ventas, trata de promocionar este platillo con promociones, descuentos o
  // en combos especiales con otros platillos más populares.
  //  Platillos huérfanos: No son ni lucrativos ni populares.
  //  no pierdas el tiempo, líbrate de estos platillos tan pronto como puedas, y olvídate de ellos.

  //    Beneficio bruto = Precio de venta - Costo de bienes (inventario)
  public Uni<Double> getGrossProfit() {
    //    Recipe.find("id")//.<Product>filter("getProduct").stream()
    //        .<Recipe>filter((Recipe recipe)->{recipe.getIngredients().stream().flatMap(ingredient
    // -> ingredient.)});
    // TODO
    Multi.createFrom().items("a", "b", "c");
    return Recipe.<Recipe>streamAll()
        .map(
            recipe -> {
              double salePrice = recipe.getSalePrice();
              double goodsCost =
                  recipe.getIngredients().stream()
                      .map(Ingredient::getProductos)
                      .map(
                          products ->
                              products.stream()
                                  .mapToDouble(
                                      product ->
                                          product.getQuantity()
                                              * product.getArticle().getUnitAcquisitionPrice())
                                  .sum())
                      .collect(Collectors.summarizingDouble(Double::doubleValue))
                      .getSum();
              return salePrice - goodsCost;
            })
        .toUni();
  }
}
