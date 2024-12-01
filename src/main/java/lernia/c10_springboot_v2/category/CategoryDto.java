package lernia.c10_springboot_v2.category;

import lernia.c10_springboot_v2.category.entity.Category;

public record CategoryDto(String name, String symbol, String description) {

    public static CategoryDto fromCategory(Category category){
        return new CategoryDto(category.getName(), category.getSymbol(), category.getDescription());
    }
}
