package lernia.c10_springboot_v2.kategori;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController

public class KategoriController {

    KategoriService kategoriService;

    public KategoriController(KategoriService kategoriService) {
        this.kategoriService = kategoriService;
    }

    @GetMapping("/kategori")
    public List<KategoriDto> kategoriList() {
        return kategoriService.getAllKategorier();
    }

    @GetMapping("/kategori/{id}")
    public ResponseEntity<List<KategoriDto>> getKategori(@PathVariable String id) {
        List<KategoriDto> kategoriList = kategoriService.getKategori(id);
        if (kategoriList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(kategoriList);
    }


    @PostMapping("/kategori")
    public ResponseEntity<Void> createKategori(@RequestBody KategoriDto kategoriDto) {
        Integer name = kategoriService.addKategori(kategoriDto);
            return ResponseEntity.created(URI.create("/kategori/" + name)).build();
    };
}
