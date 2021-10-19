package by.c43.store.dto.producerDTO;


import by.c43.store.utils.ConstraintsMessageManager;
import by.c43.store.utils.Patterns;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProducerPasswordDTO {

    @NotBlank(message = ConstraintsMessageManager.NOT_EMPTY_ERROR)
    @NotEmpty
    @NotNull
    @Pattern(regexp = Patterns.PASSWORD, message = ConstraintsMessageManager.PASSWORD_ERROR)
    private String newPassword;

    @NotBlank(message = ConstraintsMessageManager.NOT_EMPTY_ERROR)
    @NotEmpty
    @NotNull
    @Pattern(regexp = Patterns.PASSWORD, message = ConstraintsMessageManager.PASSWORD_ERROR)
    private String oldPassword;
}
