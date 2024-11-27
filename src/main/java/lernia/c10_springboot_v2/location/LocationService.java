package lernia.c10_springboot_v2.location;

import jakarta.validation.constraints.NotNull;
import lernia.c10_springboot_v2.exceptions.GlobalExceptionHandler;
import lernia.c10_springboot_v2.location.entity.Location;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@Service
public class LocationService {

    LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationDto> getAllLocations() {
        return locationRepository.findAllByDeletedFalse().stream()
                .map(LocationDto::fromLocation) // Convert to DTO
                .toList();
    }


    public List<LocationDto> getAllPublicLocations() {
        return locationRepository.findAllByIsPrivateFalseAndDeletedFalse().stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public LocationDto getPublicLocationById(Integer id) {
        return locationRepository.findByIdAndIsPrivateFalseAndDeletedFalse(id)
//                .stream()
//                .map(LocationDto::fromLocation)
//                .toList();
                .map(LocationDto::fromLocation)
                .orElseThrow(() -> new IllegalArgumentException("Location with ID " + id + " not found"));
    }

    public List<LocationDto> getPublicLocationByCategory(Integer cat) {
        return locationRepository.findAllByIsPrivateFalseAndKategoriAndDeletedFalse(cat).stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public Integer addLocation(LocationDto locationDto) {
        Location location = new Location();

        double latitude = locationDto.latitude();
        double longitude = locationDto.longitude();

        var position = Geometries.mkPoint(new G2D(latitude, longitude), WGS84);

        location.setName(locationDto.name());
        location.setKategori(locationDto.kategori());
        location.setUserId(locationDto.userId());
        location.setIsPrivate(locationDto.isPrivate());
        location.setDescription(locationDto.description());
        location.setCoordinates(position);

        location = locationRepository.save(location);

        return location.getId();  // Return the generated ID
    }


    public List<LocationDto> getAllUserLocations(Integer userId) {
        return locationRepository.findAllByUserId(userId).stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public Location editLocation(Integer id, LocationDto locationDto) {
        Location location = locationRepository.findById(id).orElse(null);

        assert location != null;
        location.setName(locationDto.name());
        location.setKategori(locationDto.kategori());
        location.setIsPrivate(locationDto.isPrivate());
        location.setDescription(locationDto.description());

        location = locationRepository.save(location);

        return location;  // Return the generated ID
    }

    public Location removeLocation(Integer id) {
        Location location = locationRepository.findById(id).orElse(null);
        int user = 666;
        assert location != null;
        location.setDeleted(true);
        location.setDeletedBy(user);
        location = locationRepository.save(location);
        return location;
    }

    public LocationDto getLocationById(Integer id) {
        return locationRepository.findById(id)
                .map(LocationDto::fromLocation)
                .orElseThrow(() -> new IllegalArgumentException("Location with ID " + id + " not found"));
    }
}
