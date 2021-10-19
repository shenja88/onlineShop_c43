package by.c43.store.dto.productDTO;

import by.c43.store.entity.CategoryOfProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeProductDTO {
    private long id;
    @NotBlank
    @NotEmpty
    @NotNull
    private CategoryOfProduct category;
}
