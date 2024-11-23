package lernia.c10_springboot_v2.location;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

//    @PostMapping("/locations/add")
//    public ResponseEntity<Void> createLocation(@RequestBody LocationDto locationDto) {
//        int id = locationService.addLocation(locationDto);
//        return ResponseEntity.created(URI.create("/locations/" + id)).build();
//    }

    @GetMapping("/locations/public/all")
    public List<LocationDto> getPublicLocations() {
        return locationService.getAllPublicLocations();
    }

//    @GetMapping("/locations/public/{name}")
//    public ResponseEntity<List<LocationDto>> getPublicLocation(@PathVariable String name) {
//        List<LocationDto> result = locationService.getPublicLocation(name);
//
//        if(result.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(result);
//    }

    @GetMapping("/locations/public/{name}")
    public ResponseEntity<List<LocationDto>> getPublicLocation(@PathVariable String name) {
        List<LocationDto> result = locationService.getPublicLocation(name);
        return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

//        TODO:     Hämta alla publika platser inom en specifik kategori.
//        TODO:     Hämta alla platser (både publika och privata) som tillhör den inloggade användaren.
//        TODO:     Hämta alla platser inom en viss yta (radie från ett centrum eller hörn på en kvadrat).
//        TODO:     POST: Skapa en ny plats (kräver inloggning).
//        TODO:     PUT: Uppdatera en befintlig plats (kräver inloggning).

//       POST: Skapa en ny plats (kräver inloggning).
//       PUT: Uppdatera en befintlig plats (kräver inloggning).
//              Vilka fält ska kunna uppdateras?
//       DELETE: Ta bort en befintlig plats (kräver inloggning). Här kan soft
//              delete vara ett alternativ.

}
