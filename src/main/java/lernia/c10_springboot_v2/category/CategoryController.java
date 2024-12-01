package lernia.c10_springboot_v2.category;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public List<CategoryDto> categoryList() {

        return categoryService.getAllCategories();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<CategoryDto>> getcategory(@PathVariable("id") String id) {
        System.out.println(id);
        List<CategoryDto> categoryList = categoryService.getCategory(id);

        if (categoryList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(categoryList);
    }


    @PostMapping("/category")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<Void> createcategory(@RequestBody CategoryDto categoryDto) {
        Integer name = categoryService.addCategory(categoryDto);
            return ResponseEntity.created(URI.create("/category/" + name)).build();
    };
}
