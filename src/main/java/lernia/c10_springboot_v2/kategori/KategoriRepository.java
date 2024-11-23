package lernia.c10_springboot_v2.kategori;

import org.springframework.data.repository.ListCrudRepository;
import lernia.c10_springboot_v2.kategori.entity.Kategori;

import java.util.List;

public interface KategoriRepository extends ListCrudRepository<Kategori, Integer> {
    List<Kategori> findByNameContaining(String name);
    boolean existsByName(String name);
}
