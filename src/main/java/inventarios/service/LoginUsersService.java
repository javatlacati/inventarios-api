package inventarios.service;

import inventarios.to.authorization.LoginUser;
import io.smallrye.mutiny.Uni;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.logging.Level;

@ApplicationScoped
@Log
public class LoginUsersService {
  Uni<List<LoginUser>> findAllLoginUsers() {
    return LoginUser.listAll();
  }

  public Uni<LoginUser> save(LoginUser user) {
    return user.persistAndFlush();
  }

  public Uni<Boolean> login(
      String userName, String secret) { // TODO perhaps make final but need workaround for mockito
    log.log(Level.INFO, "authenticating user: {0}", userName);
    Uni<LoginUser> foundLU =
        LoginUser.find(
                "select lu from LoginUser lu where userName = ?1 and password = ?2",
                userName,
                secret)
            .<LoginUser>firstResult()
            .log();
    return foundLU.map(LoginUser::isActive).onFailure().recoverWithItem(Boolean.FALSE);
  }
}
