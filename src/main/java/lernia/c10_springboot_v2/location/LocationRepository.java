package lernia.c10_springboot_v2.location;

import org.springframework.data.repository.ListCrudRepository;
import lernia.c10_springboot_v2.location.entity.Location;
import java.util.List;

public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    List<Location> findAllByIsPrivateIsFalseAndNameContainsIgnoreCase(String name);
    List<Location> findByIsPrivateIsFalse();
}



