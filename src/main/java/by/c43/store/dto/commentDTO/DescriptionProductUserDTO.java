package by.c43.store.dto.commentDTO;

import by.c43.store.entity.Product;
import by.c43.store.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DescriptionProductUserDTO {

    @NotBlank
    @NotNull
    @NotEmpty
    private String description;

    private User user;
    private long id;
}
