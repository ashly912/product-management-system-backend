package first_project.example.Product.management.system.service;

import first_project.example.Product.management.system.dto.CategoryDto;
import first_project.example.Product.management.system.entity.Category;
import first_project.example.Product.management.system.exception.ResourceNotFoundException;
import first_project.example.Product.management.system.mapper.CategoryMapper;
import first_project.example.Product.management.system.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public CategoryDto addCategory(CategoryDto categoryDto) {
        //map to category method
        Category category = CategoryMapper.mapToCategory(categoryDto);
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(category);
    }

    public Category findCategoryByName(String categoryName) {
        return categoryRepository.findCategoryByName(categoryName)
                .orElseThrow(() -> new ResourceNotFoundException("category " + categoryName + " cannot be found!"));
    }

    public List<CategoryDto> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            categoryDtos.add(CategoryMapper.mapToCategoryDto(category));
        }
        return categoryDtos;
    }

    public String deleteCategory(Long id) {
        Category category = categoryRepository.findCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("the category with id: " + id + " cannot be found!"));
        categoryRepository.delete(category);
        return "Category: " + id + " is deleted successfully!";
    }
}
