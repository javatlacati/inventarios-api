package inventarios.controller;

import inventarios.service.LoginUsersService;
import io.smallrye.mutiny.Uni;
import lombok.extern.java.Log;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import javax.inject.Inject;
import java.util.logging.Level;

@GraphQLApi
@Log
public class SimpleAuthenticationController {
  @Inject LoginUsersService loginUsersService;

  @Query
  public Uni<Boolean> hasAccess(String userName, String secret) {
    log.log(Level.FINE, "User: {0}", userName);
    return loginUsersService.login(userName, secret);
  }
}
