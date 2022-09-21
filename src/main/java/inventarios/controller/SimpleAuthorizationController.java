package inventarios.controller;

import inventarios.service.authorization.AuthorizationService;
import inventarios.to.authorization.LoginUserHasRole;
import inventarios.to.authorization.Permission;
import inventarios.to.authorization.UserRole;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import java.util.List;

@GraphQLApi
public class SimpleAuthorizationController {
  @Inject AuthorizationService authorizationService;

  @Query
  public Uni<Boolean> hasPermission(String userName, String permissionName) {
    return authorizationService.userHasPermission(userName, permissionName);
  }

  @Mutation
  public Uni<LoginUserHasRole> addNewSystemUser(LoginUserHasRole loginUserWithRole) {
    return authorizationService.addNewSystemUser(loginUserWithRole);
  }

  @Query
  public Uni<List<Permission>> getUserPermissions(String userName) {
    return authorizationService.getUserPermissions(userName);
  }

  @Mutation
  public Uni<UserRole> createRole(String roleName) {
    return authorizationService.createRole(roleName);
  }
}
