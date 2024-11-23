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
        return locationRepository.findByIsPrivateIsFalse().stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public List<LocationDto> getPublicLocation(String name) {
        return locationRepository.findAllByIsPrivateIsFalseAndNameContainsIgnoreCase(name.trim())
                .stream()
                .map(LocationDto::fromLocation)
                .toList();
    }


    public int addLocation(LocationDto locationDto) {
        Location locations = new Location();

        locations.setKategori(locationDto.kategori());
        locations.setUserId(locationDto.userId());
        locations.setIsPrivate(locationDto.isPrivate());
        locations.setDescription(locationDto.description());

        locations = locationRepository.save(locations);

        return locations.getId();
    }

}
