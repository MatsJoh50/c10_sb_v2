package lernia.c10_springboot_v2.location;

import jakarta.validation.Valid;
import lernia.c10_springboot_v2.location.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {
    @Autowired

    LocationService locationService;

    public LocationController(LocationService locationService) {

        this.locationService = locationService;
    }

    @GetMapping("/locations/{id}")
    public LocationDto getLocationById(@PathVariable("id") Integer id) {
        return locationService.getLocationById(id);
    }

    @GetMapping("/locations/all")
    public List<LocationDto> getAllLocations() {
        return locationService.getAllLocations();
    }


    @GetMapping("/locations/category/{cat}")
    public List<LocationDto> getLocationByCategory(@PathVariable("cat") Integer cat) {
        return locationService.getLocationByCategory(cat);
    }

    @GetMapping("/locations/radius/{lat}/{lon}/{dist}")
    public ResponseEntity<List<LocationDto>> getLocationById(@PathVariable("lat") Double lat, @PathVariable("lon") Double lon, @PathVariable("dist") Integer dist) {
        List<LocationDto> foundLocations = locationService.findLocationsWithinDistance(lat, lon, dist);
        return ResponseEntity.status(HttpStatus.OK).body(foundLocations);
    }



    @PostMapping("/locations")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> addLocation(@RequestBody LocationDto locationDto) {
        int id = locationService.addLocation(locationDto);
        System.out.println(id);
        return ResponseEntity.created(URI.create("/locations/" + id)).build();
    }

    @PutMapping("/locations/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> editLocation(@PathVariable("id") Integer id, @Valid @RequestBody LocationDto locationDto) {
        locationService.editLocation(id, locationDto);
        return ResponseEntity.status(HttpStatus.OK).body("ID: " + id + " was succesufully edited");
    }

    @GetMapping("/locations/remove/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> removeLocation(@PathVariable("id") Integer id) {
        Location removeLocation = locationService.removeLocation(id);

        return ResponseEntity.status(HttpStatus.OK).body("Location has been removed: \n\t"
                + "ID: " + removeLocation.getId()
                + "\n\tName: " + removeLocation.getName());
    }
}
