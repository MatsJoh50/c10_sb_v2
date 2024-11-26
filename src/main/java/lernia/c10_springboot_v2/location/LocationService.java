package lernia.c10_springboot_v2.location;

import lernia.c10_springboot_v2.location.entity.Location;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocationService {

    LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationDto> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(LocationDto::fromLocation) // Convert to DTO
                .toList();
    }


    public List<LocationDto> getAllPublicLocations(){
        return locationRepository.findAllByIsPrivateIsFalse().stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public List<LocationDto> getPublicLocationById(Integer id) {
        return locationRepository.findByIdAndIsPrivateFalse(id).stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public List<LocationDto> getPublicLocationByCategory(Integer cat) {
        return locationRepository.findAllByIsPrivateIsFalseAndKategori(cat).stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public Location addLocation(LocationDto locationDto) {
        Location location = new Location();

        location.setName(locationDto.name());
        location.setKategori(locationDto.kategori());
        location.setUserId(locationDto.userId());
        location.setIsPrivate(locationDto.isPrivate());
        location.setDescription(locationDto.description());
        location.setLatitude(locationDto.latitude());
        location.setLongitude(locationDto.longitude());

        location = locationRepository.save(location);

        return location;  // Return the generated ID
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
}
