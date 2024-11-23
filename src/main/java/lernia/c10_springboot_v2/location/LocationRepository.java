package lernia.c10_springboot_v2.location;

import org.springframework.data.repository.ListCrudRepository;
import lernia.c10_springboot_v2.location.entity.Location;
import java.util.List;
import java.util.Optional;

public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    Optional<Location> findAllByIsPrivateIsFalseAndNameContainsIgnoreCase(String name);
    List<Location> findByIsPrivateIsFalse();
}



