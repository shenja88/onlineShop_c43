package by.c43.store.dto.productDTO;

import by.c43.store.utils.ConstraintsMessageManager;
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
public class DescriptionProductDTO {

    private long id;
    @NotBlank(message = ConstraintsMessageManager.NOT_EMPTY_ERROR)
    @NotNull
    @NotEmpty
    private String description;
}
