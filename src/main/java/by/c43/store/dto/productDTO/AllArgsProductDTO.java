package by.c43.store.dto.productDTO;

import by.c43.store.entity.CategoryOfProduct;
import by.c43.store.utils.ConstraintsMessageManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllArgsProductDTO {

    @NotBlank
    @Size(min = 3, max = 100, message = ConstraintsMessageManager.NAME_PRODUCT_ERROR)
    private String name;

    @NotEmpty
    @NotBlank(message = ConstraintsMessageManager.NOT_EMPTY_ERROR)
    private String description;

    @NotEmpty
    @NotBlank(message = ConstraintsMessageManager.NOT_EMPTY_ERROR)
    private CategoryOfProduct category;

    @NotEmpty
    @NotBlank(message = ConstraintsMessageManager.PICTURE_ERROR)
    private String picture;

    private double price;
}
