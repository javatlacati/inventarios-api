package inventarios.service;

import inventarios.to.recipes.Ingredient;
import inventarios.to.recipes.Recipe;
import inventarios.to.recipes.RecipePreparationDetails;
import inventarios.to.stock.Article;
import inventarios.to.stock.Product;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@QuarkusTest
public class RecipeServiceTest {
  @Inject RecipeService sut;

  //  @Test
  //  void getRecipeList() {}
  //
  //  @Test
  //  void getStarRecipes() {}

  @Test
  void getGrossProfit() {
    MockedStatic<PanacheEntityBase> entity = mockStatic(PanacheEntityBase.class);
    Article cerveza =
        new Article(
            1L, new Date(), new Date(), new Date(), "1L cerveza indio", 2, 25, 14d, "123", null);
    List<Product> productos = Arrays.asList(new Product(1L, cerveza, 2L));
    List<Ingredient> ingredients = Arrays.asList(new Ingredient(3L, productos, ""));

    entity
        .when(PanacheEntityBase::streamAll)
        .thenReturn(
            Multi.createFrom()
                .item(
                    new Recipe(
                        1l,
                        "michelada",
                        "bebidas",
                        false,
                        null,
                        56.5d,
                        ingredients,
                        new RecipePreparationDetails(
                            2L, 2, 1, "escarchar vaso, echar cheve", 12))));
    // given();
    //        Tomemos el ejemplo de una hamburguesa.
    //        Imagina que vendes una hamburguesa por $5,
    //        pero el costo de los productos (como carne de res, queso, etc.) para hacer esa
    // hamburguesa es de $ 2.5.
    //
    //                Beneficio bruto = 5 - 2.5 = $ 2.5
    //        Margen de beneficio bruto = 2,5 / 5 x 100 = 50%
    //                Este número da una idea de la eficiencia de tu restaurante. Sin embargo, no
    // revela las ganancias reales del restaurante. Esto se debe a que la ganancia bruta no
    // representa todos los costos de funcionamiento de tu restaurante

    UniAssertSubscriber<Double> tester =
        sut.getGrossProfit()
            .invoke(aDouble -> assertEquals(0d, aDouble))
            .subscribe()
            .withSubscriber(UniAssertSubscriber.create());
//    tester.awaitItem().assertCompleted();
  }
}
