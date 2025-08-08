package first_project.example.Product.management.system.service;

import first_project.example.Product.management.system.dto.ProductDto;
import first_project.example.Product.management.system.entity.Category;
import first_project.example.Product.management.system.entity.Product;
import first_project.example.Product.management.system.entity.Supplier;
import first_project.example.Product.management.system.exception.ResourceNotFoundException;
import first_project.example.Product.management.system.mapper.ProductMapper;
import first_project.example.Product.management.system.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ProductService {
    @Autowired
    SupplierService supplierService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryService categoryService;

    public ProductDto createProduct(ProductDto productDto) {
        Category category = categoryService.findCategoryByName(productDto.getCategoryName());
        Supplier supplier = supplierService.findSupplierByName(productDto.getSupplierName());
        Product product = ProductMapper.mapProductDtoToProduct(productDto, category, supplier);
        productRepository.save(product);
        return ProductMapper.mapProductToProductDto(product);
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("can not find product with id: " + id));
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

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("the product with id: " + id + " does not exist"));
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());

        Category category = categoryService.findCategoryByName(productDto.getCategoryName());
        product.setCategory(category);

        Supplier supplier = supplierService.findSupplierByName(productDto.getSupplierName());
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

}
