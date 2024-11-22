package lernia.c10_springboot_v2.kategori;

import lernia.c10_springboot_v2.kategori.entity.Kategori;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategoriService {

    KategoriRepository kategoriRepository;

    public KategoriService(KategoriRepository kategoriRepository) {
        this.kategoriRepository = kategoriRepository;
    }


    public List<KategoriDto> getAllKategorier() {
        return kategoriRepository.findAll().stream()
                .map(KategoriDto::fromKategori)
                .toList();
    }

    public Integer addKategori(KategoriDto kategoriDto) {
        if (kategoriRepository.existsByName(kategoriDto.name())) {
            throw new IllegalArgumentException("Kategorin existerar redan");
        }

        Kategori kategori = new Kategori();
        kategori.setName(kategoriDto.name());
        kategori.setSymbol(kategoriDto.symbol());
        kategori.setDescription(kategoriDto.description());

        kategori = kategoriRepository.save(kategori);
        return kategori.getId();
    }

    public List<KategoriDto> getKategori(String name) {
        return kategoriRepository.findAll().stream()
                .filter(k -> k.getName().equalsIgnoreCase(name)) // Filter by name
//                .map(k -> new KategoriDto(k.getName(), k.getSymbol(), k.getDescription())) // Map to DTO
                .map(KategoriDto::fromKategori)
                .toList();
    }

}
