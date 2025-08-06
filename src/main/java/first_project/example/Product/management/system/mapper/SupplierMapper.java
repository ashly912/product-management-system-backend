package first_project.example.Product.management.system.mapper;

import first_project.example.Product.management.system.dto.SupplierDto;
import first_project.example.Product.management.system.entity.Supplier;

public class SupplierMapper {
    public static SupplierDto mapToSupplierDto(Supplier supplier) {
        return new SupplierDto(
                supplier.getId(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getPhone()
                );
    }

    public static Supplier mapToSupplier(SupplierDto supplierDto) {
       Supplier supplier = new Supplier();
       supplier.setName(supplierDto.getName());
       supplier.setEmail(supplierDto.getEmail());
       supplier.setPhone(supplierDto.getPhone());
       return supplier;
    }
}
