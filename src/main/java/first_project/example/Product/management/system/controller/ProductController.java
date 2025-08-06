package first_project.example.Product.management.system.controller;

import first_project.example.Product.management.system.dto.ProductDto;
import first_project.example.Product.management.system.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/productManagement/product")
public class ProductController {
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
       ProductDto productDto =  productService.getProductById(id);
       return ResponseEntity.ok(productDto);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto addProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(addProduct,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                                    @RequestBody ProductDto productDto) {
        ProductDto updateProductDto = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(updateProductDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        String mes = productService.deleteProduct(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", mes);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

}
