package by.c43.store.dto.producerDTO;

import by.c43.store.utils.ConstraintsMessageManager;
import by.c43.store.utils.Patterns;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EmailPasswordProducerDTO {

    @NotBlank
    @Size(min = 6, max = 100)
    @Pattern(regexp = Patterns.EMAIL, message = ConstraintsMessageManager.EMAIL_ERROR)
    private String email;

    @NotBlank
    @Pattern(regexp = Patterns.PASSWORD, message = ConstraintsMessageManager.PASSWORD_ERROR)
    private String password;
}
