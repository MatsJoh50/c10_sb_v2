package lernia.c10_springboot_v2.location;

import lernia.c10_springboot_v2.kategori.KategoriDto;
import lernia.c10_springboot_v2.kategori.KategoriService;
import lernia.c10_springboot_v2.location.entity.Locations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class LocationController {

    LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


    @GetMapping("/locations")
    public List<LocationDto> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping("/locations")
    public ResponseEntity<Void> createLocation(@RequestBody LocationDto locationDto) {
        int id = locationService.addLocation(locationDto);
        return ResponseEntity.created(URI.create("/locations/" + id)).build();
    }

    // ##### GET
//        TODO:     Hämta alla publika platser eller en specifik publik plats (föranonyma användare).
    @GetMapping("/locations/public")
    public ResponseEntity<List<LocationDto>> getPublicLocations() {
        return ResponseEntity.ok(locationService.getAllPublicLocations());
    }

    @GetMapping("/locations/public/{name}")
    public ResponseEntity<List<LocationDto>> getPublicLocation(@PathVariable String name) {
        List<LocationDto> result = locationService.getPublicLocation(name);

        if(result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
//        return ResponseEntity.created(URI.create("/locations/public/" + result)).build();
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
