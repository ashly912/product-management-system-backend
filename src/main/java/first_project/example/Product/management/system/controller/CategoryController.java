package first_project.example.Product.management.system.controller;

import first_project.example.Product.management.system.dto.CategoryDto;
import first_project.example.Product.management.system.entity.Category;
import first_project.example.Product.management.system.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/productManagement/category")
public class CategoryController {
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto addCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(addCategory, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAllCategories() {
        List<CategoryDto> categories = categoryService.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/search")
    public ResponseEntity<Category> findCategoryByName(@RequestParam String name) {
        Category category = categoryService.findCategoryByName(name);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        String mes = categoryService.deleteCategory(id);
        return new ResponseEntity<>(mes, HttpStatus.OK);

    }
}
