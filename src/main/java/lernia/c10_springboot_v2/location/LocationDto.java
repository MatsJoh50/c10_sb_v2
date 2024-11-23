package lernia.c10_springboot_v2.location;


import lernia.c10_springboot_v2.location.entity.Location;

public record LocationDto(
        Integer id,             // ID from the schema (primary key, auto-incremented)
        String name,            // Name of the location
        Integer kategori,       // Category (kategori) of the location
        Integer userId,         // User ID who owns the location
        Boolean isPrivate,// Boolean indicating if the location is private
        String description,     // Description of the location
        Double longitude,       // Longitude of the location
        Double latitude         // Latitude of the location

) {

    // Method to convert a Locations entity to LocationDto
    public static LocationDto fromLocation(Location location) {
        return new LocationDto(
                location.getId(),
                location.getName(),
                location.getKategori(),
                location.getUserId(),
                location.getIsPrivate(),
                location.getDescription(),
                location.getLongitude(),
                location.getLatitude()
        );
    }
}

