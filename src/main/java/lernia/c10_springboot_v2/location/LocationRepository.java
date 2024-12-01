package lernia.c10_springboot_v2.location;

import org.geolatte.geom.Box;
import org.geolatte.geom.Position;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import lernia.c10_springboot_v2.location.entity.Location;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    Optional<Location> findByIdAndIsPrivateFalseAndDeletedFalse(Integer id);

    Optional<Location> findByIdAndDeletedFalse(Integer id);

    Optional<Location> findByIdAndUserIdAndDeletedIsFalse(Integer id, String userId);

    List<Location> findAllByIsPrivateFalseAndKategoriAndDeletedFalse(Integer kat);

    List<Location> findAllByIsPrivateFalseAndDeletedFalse();

    List<Location> findAllByUserIdOrIsPrivateFalseAndDeletedIsFalse(String userId);

    List<Location> findAllByKategoriAndUserIdOrIsPrivateFalse(Integer kategori, String userId);

        @Query(value = """
        SELECT id, name,
               (6371 * ACOS(COS(RADIANS(:lat))\s
                            * COS(RADIANS(latitude))\s
                            * COS(RADIANS(longitude) - RADIANS(:lon))\s
                            + SIN(RADIANS(:lat))\s
                            * SIN(RADIANS(latitude)))) AS distance
        FROM locations
        WHERE deleted = false AND is_private = false
        HAVING distance <= :distance
        ORDER BY distance ASC
       \s""", nativeQuery = true)
        List<Location> findLocationsWithinDistance(@Param("lat") double latitude,
                                                   @Param("lon") double longitude,
                                                   @Param("distance") double distance);

}



