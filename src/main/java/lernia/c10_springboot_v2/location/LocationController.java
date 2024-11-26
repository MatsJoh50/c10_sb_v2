package lernia.c10_springboot_v2.location;

import jakarta.validation.Valid;
import lernia.c10_springboot_v2.location.entity.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {

    LocationService locationService;

    public LocationController(LocationService locationService) {

        this.locationService = locationService;
    }


    @GetMapping("/locations/all")
    public List<LocationDto> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/locations/public/all")
    public List<LocationDto> getPublicLocations() {
        return locationService.getAllPublicLocations();
    }


    @GetMapping("/locations/public/{id}")
    public List<LocationDto> getPublicLocationById(@PathVariable("id") Integer id) {
        return locationService.getPublicLocationById(id);
    }

    //        TODO:     Hämta alla publika platser inom en specifik kategori.
    @GetMapping("/locations/public/category/{cat}")
    public List<LocationDto> getPublicLocationByCategory(@PathVariable("cat") Integer cat) {
        return locationService.getPublicLocationByCategory(cat);
    }

    //        TODO:     POST: Skapa en ny plats (kräver inloggning).
    @PostMapping("/locations/add")
    public ResponseEntity<Location> addLocation(@Valid @RequestBody LocationDto locationDto) {
        Location savedLocation = locationService.addLocation(locationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLocation);
    }

//        TODO:     Hämta alla platser (både publika och privata) som tillhör den inloggade användaren.
    @GetMapping("/locations/{userId}")
    public List<LocationDto> getLocationsByUserId(@PathVariable("userId") Integer userId) {
        return locationService.getAllUserLocations(userId);
    }
//        TODO:     PUT: Uppdatera en befintlig plats (kräver inloggning).
    @PutMapping("/locations/edit/{id}")
    public ResponseEntity<Location> editLocation(@PathVariable("id") Integer id, @Valid @RequestBody LocationDto locationDto) {
        Location editLocation = locationService.editLocation(id, locationDto);
        return ResponseEntity.status(HttpStatus.OK).body(editLocation);
    }
//        TODO:     Hämta alla platser inom en viss yta (radie från ett centrum eller hörn på en kvadrat).
//        TODO:       DELETE: Ta bort en befintlig plats (kräver inloggning). Här kan soft
//                          delete vara ett alternativ.

}
