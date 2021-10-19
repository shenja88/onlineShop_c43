package by.c43.store.dto.productDTO;

import by.c43.store.utils.ConstraintsMessageManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PictureProductDTO {
    private long id;
    @NotBlank(message = ConstraintsMessageManager.PICTURE_ERROR)
    @NotEmpty
    @NotNull
    private String picture;
}
