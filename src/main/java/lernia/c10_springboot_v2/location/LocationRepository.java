package lernia.c10_springboot_v2.location;

import org.springframework.data.repository.ListCrudRepository;
import lernia.c10_springboot_v2.location.entity.Locations;

import java.util.List;

public interface LocationRepository extends ListCrudRepository<Locations, Long> {
    List<Locations> findByPrivateLocationIsFalse();
    List<Locations> findAllByPrivateLocationIsFalseAndNameContainsIgnoreCase(String name);

}



