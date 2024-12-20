package lernia.c10_springboot_v2.location;

import lernia.c10_springboot_v2.location.entity.Location;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    public List<LocationDto> getAllLocations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            String userId =  authentication.getName();
            return locationRepository.findAllByUserIdOrIsPrivateFalseAndDeletedIsFalse(userId).stream()
                    .map(LocationDto::fromLocation)
                    .toList();
        } else {
            return locationRepository.findAllByIsPrivateFalseAndDeletedFalse().stream()
                    .map(LocationDto::fromLocation)
                    .toList();
        }
    }

    public LocationDto getLocationById(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            return locationRepository.findByIdAndUserIdAndDeletedIsFalse(id, authentication.getName())
                    .map(LocationDto::fromLocation)
                    .orElseThrow(() -> new IllegalArgumentException("Location with ID " + id + " not found"));
        } else {
            return locationRepository.findByIdAndIsPrivateFalseAndDeletedFalse(id)
                    .map(LocationDto::fromLocation)
                    .orElseThrow(() -> new IllegalArgumentException("Location with ID " + id + " not found"));
        }
    }

    public List<LocationDto> getLocationByCategory(Integer cat) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            String userId = authentication.getName();
            return locationRepository.findAllByKategoriAndUserIdOrIsPrivateFalse(cat, userId).stream()
                  .map(LocationDto::fromLocation)
                  .toList();
        } else {
            return locationRepository.findAllByIsPrivateFalseAndKategoriAndDeletedFalse(cat).stream()
                    .map(LocationDto::fromLocation)
                    .toList();
        }
    }


    public List<LocationDto> findLocationsWithinDistance(double latitude, double longitude, double distance) {
        List<LocationDto> allLocations = locationRepository.findAllByIsPrivateFalseAndDeletedFalse().stream()
                .map(LocationDto::fromLocation)
                .toList();
        return allLocations.stream()
                .filter(location -> calculateDistance(latitude, longitude, location.latitude(), location.longitude()) <= distance)
                .sorted(Comparator.comparingDouble(location ->
                        calculateDistance(latitude, longitude, location.latitude(), location.longitude())))
                .toList();
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final double EARTH_RADIUS_KM = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }


    public Integer addLocation(LocationDto locationDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {

            String userId = authentication.getName();

            Location location = new Location();

            double latitude = locationDto.latitude();
            double longitude = locationDto.longitude();

            var position = Geometries.mkPoint(new G2D(latitude, longitude), WGS84);

            location.setName(locationDto.name());
            location.setKategori(locationDto.kategori());
            location.setUserId(userId);
            location.setIsPrivate(locationDto.isPrivate());
            location.setDescription(locationDto.description());
            location.setCoordinates(position);

            location = locationRepository.save(location);

            return location.getId();  // Return the generated ID
        } else {
            throw new SecurityException("Authentication required");
        }
    }

    public void editLocation(Integer id, LocationDto locationDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {

            if(getLocationById(id) == null) {
                throw new SecurityException("Authentication required");
            }

            Location location = locationRepository.findById(id).orElse(null);

            assert location != null;
            location.setName(locationDto.name());
            location.setKategori(locationDto.kategori());
            location.setIsPrivate(locationDto.isPrivate());
            location.setDescription(locationDto.description());

            location = locationRepository.save(location);

        } else {
            throw new SecurityException("Authentication required");
        }
    }

    public Location removeLocation(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {

            if(getLocationById(id) == null) {
                throw new SecurityException("Authentication required");
            }

            Location location = locationRepository.findById(id).orElse(null);
            assert location != null;
            location.setDeleted(true);
            location.setDeletedBy(authentication.getName());
            location = locationRepository.save(location);
            return location;
        } else {
            throw new SecurityException("Authentication required");
        }
    }


}
