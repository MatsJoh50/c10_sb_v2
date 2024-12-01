package lernia.c10_springboot_v2.category;

import org.springframework.data.repository.ListCrudRepository;
import lernia.c10_springboot_v2.category.entity.Category;

import java.util.List;

public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
    List<Category> findByNameContaining(String name);
    boolean existsByName(String name);
}
