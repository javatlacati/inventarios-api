package inventarios.controller;

import inventarios.service.StockService;
import org.eclipse.microprofile.graphql.GraphQLApi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@GraphQLApi
public class StockController {
  @Inject StockService stockService;
}
