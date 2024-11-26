package lernia.c10_springboot_v2.location;

import org.springframework.data.repository.ListCrudRepository;
import lernia.c10_springboot_v2.location.entity.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    List<Location> findAllByDeletedFalse();

    Optional<Location> findByIdAndIsPrivateFalseAndDeletedFalse(Integer id);

    List<Location> findAllByIsPrivateFalseAndKategoriAndDeletedFalse(Integer kat);

    List<Location> findAllByIsPrivateFalseAndDeletedFalse();

    List<Location> findAllByUserId(Integer userId);
}



