package inventarios.util;

import inventarios.repository.AuthorizationRepository;
import inventarios.to.authorization.LoginUser;
import inventarios.to.authorization.LoginUserHasRole;
import inventarios.to.authorization.Permission;
import inventarios.to.authorization.UserRole;
import inventarios.to.personnel.EmployeeDetail;
import inventarios.to.personnel.Salary;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

// @ApplicationScoped
//@QuarkusMain
public class DataProvider {
  public static void main(String[] args) {
    Quarkus.run(CustomApp.class, args);
  }

  public static class CustomApp implements QuarkusApplication {
//    @Inject AuthorizationRepository authorizationRepository;

    //  void onStart(@Observes StartupEvent ev) {
    //    //    fillDatabase();
    //  }

    @Transactional
    private void fillDatabase() {
      System.out.println("Filling database...");
      Salary oscarSalary = new Salary(null, "mensual", 1600d, new Date(), null);
      EmployeeDetail oscarDetails =
          new EmployeeDetail(
              null,
              "4165465465",
              "sean",
              "herbert",
              "collins",
              "some addrees #2324",
              "manager",
              "08:45 AM",
              "5:45 PM",
              oscarSalary);
      LoginUser oscar = new LoginUser(null, "oscar", "oscar", oscarDetails, true);
      Permission viewAdminMenu = new Permission(null, "AdminMenu");
      Permission acceptOrder = new Permission(null, "ApproveRequisition");
      Permission addNewSystemUser = new Permission(null, "AddUser");
      Permission approveNewSystemUser = new Permission(null, "AproveUserCreation");
      Permission getStatistics = new Permission(null, "GetStatistics");
      Permission deactivateUser = new Permission(null, "DeactivateUser");
      Permission receiveMerchandise = new Permission(null, "AcceptMerchandise");

      UserRole adminRole =
          new UserRole(
              null,
              "Admin",
              Arrays.asList(
                  viewAdminMenu,
                  acceptOrder,
                  addNewSystemUser,
                  approveNewSystemUser,
                  getStatistics,
                  deactivateUser,
                  receiveMerchandise),
              null);
      List<UserRole> oscarRoles = Collections.singletonList(adminRole);
      System.out.println("finished computing entities, proceeding to persist");
      try {
        TimeUnit.SECONDS.sleep(10);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
//      authorizationRepository
//          .addNewSystemUser(new LoginUserHasRole(null, oscar, oscarRoles))
//          .subscribe()
//          .with(System.out::println);
    }

    @Override
    public int run(String... args) throws Exception {
//      fillDatabase();
      return 0;
    }
  }
}
