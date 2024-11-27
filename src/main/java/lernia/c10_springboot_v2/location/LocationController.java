package lernia.c10_springboot_v2.location;

import jakarta.validation.Valid;
import lernia.c10_springboot_v2.location.entity.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class LocationController {

    LocationService locationService;

    public LocationController(LocationService locationService) {

        this.locationService = locationService;
    }

    @GetMapping("/locations/{id}")
    public LocationDto getPublicLocationById(@PathVariable("id") Integer id) {
        return locationService.getPublicLocationById(id);
    }

    @GetMapping("/locations/all")
    public List<LocationDto> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/locations/public/all")
    public List<LocationDto> getPublicLocations() {
        return locationService.getAllPublicLocations();
    }

    //        TODO:     Hämta alla publika platser inom en specifik kategori.
    @GetMapping("/locations/public/category/{cat}")
    public List<LocationDto> getPublicLocationByCategory(@PathVariable("cat") Integer cat) {
        return locationService.getPublicLocationByCategory(cat);
    }

    //        TODO:     POST: Skapa en ny plats (kräver inloggning).
    @PostMapping("/locations")
    public ResponseEntity<String> addLocation(@RequestBody LocationDto locationDto) {
        int id = locationService.addLocation(locationDto);
        System.out.println(id);
        return ResponseEntity.created(URI.create("/locations/" + id)).body("Location created with ID: " + id);
    }


    //        TODO:     Hämta alla platser (både publika och privata) som tillhör den inloggade användaren.
    @GetMapping("/locations/user/{userId}")
    public List<LocationDto> getLocationsByUserId(@PathVariable("userId") Integer userId) {
        return locationService.getAllUserLocations(userId);
    }

    //        TODO:     PUT: Uppdatera en befintlig plats (kräver inloggning).
    @PutMapping("/locations/edit/{id}")
    public ResponseEntity<String> editLocation(@PathVariable("id") Integer id, @Valid @RequestBody LocationDto locationDto) {
                locationService.editLocation(id, locationDto);
        return ResponseEntity.status(HttpStatus.OK).body("ID: " + id + " was succesufully edited");
    }
//        TODO:     Hämta alla platser inom en viss yta (radie från ett centrum eller hörn på en kvadrat).

    //        TODO:       DELETE: Ta bort en befintlig plats (kräver inloggning). Här kan soft
//                          delete vara ett alternativ.
    @GetMapping("/locations/remove/{id}")
    public ResponseEntity<String> removeLocation(@PathVariable("id") Integer id) {
        Location removeLocation = locationService.removeLocation(id);

        return ResponseEntity.status(HttpStatus.OK).body("Location has been removed: \n\t"
                + "ID: " + removeLocation.getId()
                + "\n\tName: " + removeLocation.getName());
    }
}
