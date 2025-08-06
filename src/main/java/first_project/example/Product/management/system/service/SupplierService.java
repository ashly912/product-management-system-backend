package first_project.example.Product.management.system.service;

import first_project.example.Product.management.system.dto.SupplierDto;
import first_project.example.Product.management.system.entity.Supplier;
import first_project.example.Product.management.system.exception.ResourceNotFoundException;
import first_project.example.Product.management.system.mapper.SupplierMapper;
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
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public List<SupplierDto> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        List<SupplierDto> supplierDto = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            supplierDto.add(SupplierMapper.mapToSupplierDto(supplier));
        }
        return supplierDto;
    }

    public Supplier findSupplierByName(String name) {
        return supplierRepository.findSupplierByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("supplier " + name + " cannot be found!"));
    }

    public SupplierDto createSupplier(SupplierDto supplierDto) {
        Supplier supplier = SupplierMapper.mapToSupplier(supplierDto);
        supplierRepository.save(supplier);
        return SupplierMapper.mapToSupplierDto(supplier);
    }

    public String deleteSupplier(Long SupplierId) {
        Supplier supplier = supplierRepository.findById(SupplierId)
                .orElseThrow(() -> new ResourceNotFoundException("please provide existing product supplier!"));
        supplierRepository.delete(supplier);
        return "Supplier with id: " + SupplierId + " was deleted successfully!";
    }

}
