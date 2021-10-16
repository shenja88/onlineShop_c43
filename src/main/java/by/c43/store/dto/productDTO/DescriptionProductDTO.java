package by.c43.store.dto.productDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DescriptionProductDTO {
    @NotBlank
    @NotNull
    @NotEmpty
    private String description;
}
