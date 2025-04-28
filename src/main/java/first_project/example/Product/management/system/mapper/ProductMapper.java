package first_project.example.Product.management.system.mapper;

import first_project.example.Product.management.system.dto.ProductDto;
import first_project.example.Product.management.system.entity.Category;
import first_project.example.Product.management.system.entity.Product;
import first_project.example.Product.management.system.entity.Supplier;

public class ProductMapper {
    public static ProductDto mapProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getName());
        productDto.setPrice(product.getPrice());

        if(product.getCategory() != null) {
            productDto.setCategoryName(product.getCategory().getCategoryName());
        }

        if(product.getSupplier() != null) {
            productDto.setBrand(product.getSupplier().getBrand());
        }
        return productDto;
    }

    public static Product mapProductDtoToProduct(ProductDto productDto,Category category,Supplier supplier) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getProductName());

        if(productDto.getCategoryName() != null) {
            product.setCategory(category);
        }
        if(productDto.getBrand() != null) {
            product.setSupplier(supplier);
        }
        return product;
    }
}
