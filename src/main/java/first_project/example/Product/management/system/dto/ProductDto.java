package first_project.example.Product.management.system.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private Long id;
    private String productName;
    private Double price;

    private String categoryName;
    private String brand;

}
