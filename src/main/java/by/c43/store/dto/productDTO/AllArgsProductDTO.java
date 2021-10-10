package by.c43.store.dto.productDTO;

import by.c43.store.entity.CategoryOfProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AllArgsProductDTO {

    @NotBlank
    @Size(min = 3, max = 100, message = "Enter a name of length 3-100")
    private String name;

    @NotBlank(message = "Field must not be empty!")
    private String description;

    @NotBlank(message = "Field must not be empty!")
    private CategoryOfProduct category;

    @NotBlank(message = "Enter a correct URL picture!")
    private String picture;

    @NotBlank(message = "Field must not be empty!")
    private double price;
}
