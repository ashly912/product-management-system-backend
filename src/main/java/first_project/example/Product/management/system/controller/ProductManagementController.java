package first_project.example.Product.management.system.controller;

import first_project.example.Product.management.system.dto.CategoryDto;
import first_project.example.Product.management.system.dto.ProductDto;
import first_project.example.Product.management.system.dto.SupplierDto;
import first_project.example.Product.management.system.service.ProductManagementService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/productManagement")
public class ProductManagementController {
    ProductManagementService productManagementService;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
       ProductDto addProduct = productManagementService.createProduct(productDto);
       return new ResponseEntity<>(addProduct,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productManagementService.getAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
       ProductDto productDto =  productManagementService.getProductById(id);
       return ResponseEntity.ok(productDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                                    @RequestBody ProductDto productDto) {
        ProductDto updateProductDto = productManagementService.updateProduct(id, productDto);
        return ResponseEntity.ok(updateProductDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        String mes = productManagementService.deleteProduct(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", mes);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @PostMapping("/category/create")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody String categoryName) {
        CategoryDto addCategory = productManagementService.createCategory(categoryName);
        return new ResponseEntity<>(addCategory, HttpStatus.CREATED);

    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        CategoryDto categoryDto = productManagementService.getCategoryById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping("/supplier/create")
    public ResponseEntity<SupplierDto> addNewSupplier(@RequestBody SupplierDto supplierDto) {
        SupplierDto newSupplierDto =  productManagementService.createSupplier(supplierDto);
        return new ResponseEntity<>(newSupplierDto, HttpStatus.CREATED);

    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<SupplierDto> getSupplierById(@PathVariable Long id) {
        SupplierDto supplierDto = productManagementService.getSupplierById(id);
        return ResponseEntity.ok(supplierDto);
    }

    @DeleteMapping("/supplier/{supplierId}")
    public ResponseEntity<String> removeSupplierById(@PathVariable Long supplierId) {
        String mes = productManagementService.deleteSupplier(supplierId);
        return new ResponseEntity<>(mes, HttpStatus.OK);

    }
}
