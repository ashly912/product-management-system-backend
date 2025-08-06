package first_project.example.Product.management.system.controller;

import first_project.example.Product.management.system.dto.SupplierDto;
import first_project.example.Product.management.system.entity.Supplier;
import first_project.example.Product.management.system.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/productManagement/supplier")
public class SupplierController {
    SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierDto>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @GetMapping("/search")
    public ResponseEntity<Supplier> getSupplierByName(@RequestParam String name) {
        return ResponseEntity.ok(supplierService.findSupplierByName(name));
    }

    @PostMapping("/create")
    public ResponseEntity<SupplierDto> addNewSupplier(@RequestBody SupplierDto supplierDto) {
        SupplierDto newSupplierDto =  supplierService.createSupplier(supplierDto);
        return new ResponseEntity<>(newSupplierDto, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplierById(@PathVariable Long id) {
        String mes = supplierService.deleteSupplier(id);
        return new ResponseEntity<>(mes, HttpStatus.OK);

    }
}
