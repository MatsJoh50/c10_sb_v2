package lernia.c10_springboot_v2.kategori;

import org.springframework.data.repository.ListCrudRepository;
import lernia.c10_springboot_v2.kategori.entity.Kategori;

public interface KategoriRepository extends ListCrudRepository<Kategori, Integer> {
    boolean existsByName(String name);
}
