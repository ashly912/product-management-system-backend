package first_project.example.Product.management.system.mapper;

import first_project.example.Product.management.system.dto.CategoryDto;
import first_project.example.Product.management.system.entity.Category;

public class CategoryMapper {
    public static CategoryDto mapToCategoryDto(Category category) {
        return new CategoryDto(
             category.getId(),
             category.getCategoryName()

        );
    }

   public static Category mapToCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        return category;
   }
}
