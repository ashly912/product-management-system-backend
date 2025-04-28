package first_project.example.Product.management.system.mapper;

import first_project.example.Product.management.system.dto.SupplierDto;
import first_project.example.Product.management.system.entity.Supplier;

public class SupplierMapper {
    public static SupplierDto mapToSupplierDto(Supplier supplier) {
        return new SupplierDto(
                supplier.getId(),
                supplier.getBrand(),
                supplier.getEmail(),
                supplier.getPhone()
                );
    }

    public static Supplier mapToSupplier(SupplierDto supplierDto) {
       Supplier supplier = new Supplier();
       supplier.setBrand(supplierDto.getBrand());
       supplier.setEmail(supplierDto.getEmail());
       supplier.setPhone(supplierDto.getPhone());
       return supplier;
    }
}
