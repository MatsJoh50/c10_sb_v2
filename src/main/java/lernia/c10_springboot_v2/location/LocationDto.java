package lernia.c10_springboot_v2.location;


import lernia.c10_springboot_v2.location.entity.Locations;

import java.util.Date;

public record LocationDto(Integer id,
                          String name,
                          Integer kategori,
                          Integer userId,
                          Boolean privateLocation,
                          String description
) {

    public static LocationDto fromLocation(Locations location) {
        return new LocationDto(
                location.getId(),
                location.getName(),
                location.getKategori(),
                location.getUserId(),
                location.getPrivateLocation(),
                location.getDescription()
        );
    }
}
