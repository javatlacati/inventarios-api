package inventarios.service.authorization;

import inventarios.repository.AuthorizationRepository;
import inventarios.to.authorization.LoginUserHasRole;
import inventarios.to.authorization.Permission;
import inventarios.to.authorization.UserRole;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class AuthorizationService {

  @Inject AuthorizationRepository authorizationRepository;

  public Uni<UserRole> createRole(String roleName) {
    return new UserRole(null, roleName, Collections.emptyList(), Collections.emptyList())
        .persistAndFlush();
  }

  //  public Uni<Permission> addPermissionToRole(String roleName, String permissionName) {
  //    Uni<Permission> targetPermission = Permission.find("name", permissionName).firstResult();
  //    targetPermission
  //        .onFailure()
  //        .recoverWithUni(new Permission(null, permissionName).persistAndFlush())
  //        .subscribe()
  //        .with(
  //            aPermission -> {
  //              Uni<UserRole> userRole = UserRole.find("name", roleName).firstResult();
  //              userRole.subscribe().with(userRole1 ->
  // userRole1.getPermissions().add(aPermission));
  //            });
  //
  //    return targetPermission;
  //  }

  public Uni<Boolean> userHasPermission(String userName, String permissionName) {
    return authorizationRepository.userHasPermission(userName, permissionName);
  }

  public Uni<List<Permission>> getUserPermissions(String userName) {
    return authorizationRepository.getUserPermissions(userName);
  }

  public Uni<LoginUserHasRole> addNewSystemUser(LoginUserHasRole loginUserWithRole) {
    return authorizationRepository.addNewSystemUser(loginUserWithRole);
  }
}
