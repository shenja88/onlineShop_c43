package by.c43.store.dto.producerDTO;

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
    @Pattern(regexp = Patterns.EMAIL, message = "Enter a correct e-mail address")
    private String email;

    @NotBlank
    @Pattern(regexp = Patterns.PASSWORD, message = "You entered a simple password! " +
            "Password must contain a minimum of 6 characters, at least 1 uppercase letter, " +
            "1 lowercase letter, and 1 number with no spaces.")
    private String password;
}
