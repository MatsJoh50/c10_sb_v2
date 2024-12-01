package lernia.c10_springboot_v2.category;

import lernia.c10_springboot_v2.category.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository kategoriRepository) {
        this.categoryRepository = kategoriRepository;
    }


    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromCategory)
                .toList();
    }



    public List<CategoryDto> getCategory(String name) {
        return categoryRepository.findAll().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name)) // Filter by name
                .map(CategoryDto::fromCategory)
                .toList();
    }


    public Integer addCategory(CategoryDto categoryDto) {
        if (categoryRepository.existsByName(categoryDto.name())) {
            throw new IllegalArgumentException("Kategorin existerar redan");
        }

        Category category = new Category();
        category.setName(categoryDto.name());
        category.setSymbol(categoryDto.symbol());
        category.setDescription(categoryDto.description());

        category = categoryRepository.save(category);
        return category.getId();
    }
}
