package first_project.example.Product.management.system.repository;

import first_project.example.Product.management.system.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findSupplierByName(String brand);
}
