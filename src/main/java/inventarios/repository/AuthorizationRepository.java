package inventarios.repository;

import inventarios.to.authorization.LoginUser;
import inventarios.to.authorization.LoginUserHasRole;
import inventarios.to.authorization.Permission;
import inventarios.to.authorization.UserRole;
import inventarios.to.personnel.EmployeeDetail;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class AuthorizationRepository implements PanacheRepository<LoginUserHasRole> {
  public Uni<LoginUserHasRole> addNewSystemUser(LoginUserHasRole loginUserWithRole) {
    return Panache.withTransaction(
        () -> {
          LoginUser user = loginUserWithRole.getUser();
          EmployeeDetail employeedetail = user.getEmployeeDetail();
          if (null == employeedetail.getId()) {
            employeedetail.<EmployeeDetail>persist().subscribe().with(user::setEmployeeDetail);
          }
          if (null == user.getId()) {
            user.<LoginUser>persist().subscribe().with(loginUserWithRole::setUser);
          }

          List<UserRole> roles = loginUserWithRole.getRoles();
          List<UserRole> savedRoles = new ArrayList<>();
          roles.forEach(
              userRole -> {
                List<Permission> permissions = userRole.getPermissions();
                List<Permission> savedPermissions = new ArrayList<>(permissions.size());
                permissions.forEach(
                    permission -> {
                      if (null == permission.getId()) {
                        permission.<Permission>persist().subscribe().with(savedPermissions::add);
                      } else {
                        savedPermissions.add(permission);
                      }
                    });
                userRole.setPermissions(savedPermissions);
                if (null == userRole.getId()) {
                  userRole.<UserRole>persist().subscribe().with(savedRoles::add);
                } else {
                  savedRoles.add(userRole);
                }
              });
          loginUserWithRole.setRoles(savedRoles);
          return loginUserWithRole.persist();
        });
  }

  public Uni<Boolean> userHasPermission(String userName, String permissionName) {
    return LoginUserHasRole.find(
            "select pe.name from LoginUserHasRole luhr inner join luhr.user lu inner join luhr.roles ro inner join ro.permissions pe where lu.userName = :username and pe.name = :permissionName",
            Parameters.with("username", userName),
            Parameters.with("permissionName", permissionName))
        .count()
        .map(aLong -> aLong == 1L)
        .onFailure()
        .recoverWithItem(Boolean.FALSE);

    // same stuff but reactively calling each part
    // select pe.name from LoginUserHasRole luhr inner join luhr.user lu inner join luhr.roles ro
    // inner join ro.permissions pe where lu.userName = 'oscar' and pe.name = 'AdminMenu'
    //    Uni<LoginUser> user = LoginUser.find("userName", userName).firstResult();
    //    Uni<LoginUserHasRole> loginUserHasRole =
    //        user.chain(theUser -> LoginUserHasRole.find("user", theUser).firstResult());
    //    Uni<List<UserRole>> userRoles = loginUserHasRole.map(LoginUserHasRole::getRoles);
    //    Uni<Stream<UserRole>> userRoleStream = userRoles.map(Collection::stream);
    //    Uni<Stream<UserRole>> userSubRoles1stLevel =
    //        userRoles
    //            .map(Collection::stream)
    //            .map(
    //                aUserRoleStream ->
    //                    aUserRoleStream.flatMap(
    //                        userRole1 ->
    //                            Optional.ofNullable(userRole1.getSubRoles()).stream()
    //                                .flatMap(Collection::stream)));
    //    return Uni.combine()
    //        .all()
    //        .unis(userRoleStream, userSubRoles1stLevel)
    //        .combinedWith(Stream::concat)
    //        .map(roleStream -> roleStream.flatMap(userRole -> userRole.getPermissions().stream()))
    //        .map(permissionsStream -> permissionsStream.map(Permission::getName))
    //        .map(
    //            permissionNameStream ->
    //                permissionNameStream.anyMatch(
    //                    thePermissionName -> thePermissionName.equals(permissionName)))
    //        .onFailure()
    //        .recoverWithUni(Uni.createFrom().item(Boolean.FALSE));
  }

  public Uni<List<Permission>> getUserPermissions(String userName) {
    return LoginUserHasRole.find(
            "select pe from LoginUserHasRole luhr inner join luhr.user lu inner join luhr.roles ro inner join ro.permissions pe where lu.userName = :username",
            Parameters.with("username", userName))
        .list();
  }
}
