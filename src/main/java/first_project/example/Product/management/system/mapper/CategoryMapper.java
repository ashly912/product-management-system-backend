package first_project.example.Product.management.system.mapper;

import first_project.example.Product.management.system.dto.CategoryDto;
import first_project.example.Product.management.system.entity.Category;

public class CategoryMapper {
    public static CategoryDto mapToCategoryDto(Category category) {
        return new CategoryDto(
             category.getId(),
             category.getName()

        );
    }

   public static Category mapToCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return category;
   }
}
