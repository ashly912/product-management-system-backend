package first_project.example.Product.management.system.service;


import first_project.example.Product.management.system.dto.CategoryDto;
import first_project.example.Product.management.system.dto.ProductDto;
import first_project.example.Product.management.system.dto.SupplierDto;
import first_project.example.Product.management.system.entity.Category;
import first_project.example.Product.management.system.entity.Product;
import first_project.example.Product.management.system.entity.Supplier;
import first_project.example.Product.management.system.exception.ResourceNotFoundException;
import first_project.example.Product.management.system.mapper.CategoryMapper;
import first_project.example.Product.management.system.mapper.ProductMapper;
import first_project.example.Product.management.system.mapper.SupplierMapper;
import first_project.example.Product.management.system.repository.CategoryRepository;
import first_project.example.Product.management.system.repository.ProductRepository;
import first_project.example.Product.management.system.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ProductManagementService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SupplierRepository supplierRepository;

public ProductDto createProduct(ProductDto productDto) {
    Category category = categoryRepository.findCategoryByCategoryName(productDto.getCategoryName())
            .orElseThrow(() -> new ResourceNotFoundException("please add " + productDto.getProductName() +  " category!"));
    Supplier supplier = supplierRepository.findSupplierByBrand(productDto.getBrand())
            .orElseThrow(()-> new ResourceNotFoundException("please add " + productDto.getProductName() +  " supplier!"));
    Product product = ProductMapper.mapProductDtoToProduct(productDto, category, supplier);
    productRepository.save(product);
    return ProductMapper.mapProductToProductDto(product);
}

public ProductDto getProductById(Long id) {
    Product product = productRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("can not find product with id: " + id));
    return ProductMapper.mapProductToProductDto(product);
}

public List<ProductDto> getAllProducts() {
    List<Product> products = productRepository.findAll();
    List<ProductDto> productDto = new ArrayList<>();
    for (Product product : products) {
        productDto.add(ProductMapper.mapProductToProductDto(product));
    }
    return productDto;
}

public ProductDto updateProduct(Long id,ProductDto productDto) {
    Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("the product with id: " + id + " does not exist"));
    product.setName(productDto.getProductName());
    product.setPrice(productDto.getPrice());

    Category category = categoryRepository.findCategoryByCategoryName(productDto.getCategoryName())
                    .orElseThrow(()-> new ResourceNotFoundException("please provide existing product category!"));
    product.setCategory(category);

    Supplier supplier = supplierRepository.findSupplierByBrand(productDto.getBrand())
            .orElseThrow(()-> new ResourceNotFoundException("please provide existing product supplier!"));
    product.setSupplier(supplier);

    productRepository.save(product);
    return ProductMapper.mapProductToProductDto(product);
}

public String deleteProduct(Long id) {
    Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("the product with id: " + id + "is already deleted!"));
    productRepository.delete(product);
    return "Product with id: " + id + " was deleted successfully!";
}

public CategoryDto createCategory(String categoryName) {
    Category category = new Category();
    category.setCategoryName(categoryName);
    categoryRepository.save(category);
    return CategoryMapper.mapToCategoryDto(category);
}

public CategoryDto getCategoryById(Long id) {
    Category category = categoryRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("please provide existing product category!"));
    return CategoryMapper.mapToCategoryDto(category);
}

public SupplierDto createSupplier(SupplierDto supplierDto) {
    Supplier supplier = SupplierMapper.mapToSupplier(supplierDto);
    supplierRepository.save(supplier);
    return SupplierMapper.mapToSupplierDto(supplier);
}

public SupplierDto getSupplierById(Long id) {
    Supplier supplier = supplierRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("please provide existing product supplier!"));
    return SupplierMapper.mapToSupplierDto(supplier);
}

public String deleteSupplier(Long SupplierId) {
    Supplier supplier = supplierRepository.findById(SupplierId)
            .orElseThrow(()-> new ResourceNotFoundException("please provide existing product supplier!"));
    supplierRepository.delete(supplier);
    return "Supplier with id: " + SupplierId + " was deleted successfully!";
}

}
