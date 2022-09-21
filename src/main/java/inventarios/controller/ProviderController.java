package inventarios.controller;

import inventarios.to.provider.Provider;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;

@GraphQLApi
public class ProviderController {

  @Query
  public Uni<List<Provider>> getProviders() {
    return Provider.listAll();
  }

  @Mutation
  public Uni<Provider> addProvider(Provider provider) {
    return provider.persistAndFlush();
  }

//  Período de reaprovisionamiento
//  Plazo de tiempo que transcurre entre dos entregas de un mismo proveedor.
}
