package by.c43.store.dto.usersDTO;

import by.c43.store.entity.Telephone;
import by.c43.store.utils.Patterns;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AllArgUsersDTO {

    @NotBlank
    @Size(min = 3, max = 30, message = "Enter a name of length 3-30")
    private String name;

    @NotBlank
    @Size(min = 6, max = 100)
    @Pattern(regexp = Patterns.EMAIL, message = "Enter a correct e-mail account")
    private String email;

    @NotBlank
    @Pattern(regexp = Patterns.PASSWORD, message = "You entered a simple password! " +
            "Password must contain a minimum of 6 characters, at least 1 uppercase letter, " +
            "1 lowercase letter, and 1 number with no spaces.")
    private String password;

    @NotEmpty
    @NotBlank(message = "Enter a correct URL picture!")
    private String picture;

    @NotBlank
    @Size(min = 9, message = "Phone number cannot have less then 9 numbers")
    private String number;
}
