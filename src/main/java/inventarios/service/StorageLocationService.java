package inventarios.service;

import inventarios.to.infrastructure.StorageLocation;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class StorageLocationService {
  public Uni<List<StorageLocation>> findAllStorageLocations() {
    return StorageLocation.listAll();
  }
}
