package by.c43.store.dto.usersDTO;

import by.c43.store.utils.ConstraintsMessageManager;
import by.c43.store.utils.Patterns;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PasswordUserDTO {

    @NotBlank
    @Pattern(regexp = Patterns.PASSWORD, message = ConstraintsMessageManager.PASSWORD_ERROR)
    private String oldPassword;

    @NotBlank
    @Pattern(regexp = Patterns.PASSWORD, message = ConstraintsMessageManager.PASSWORD_ERROR)
    private String newPassword;

    @NotBlank
    @Pattern(regexp = Patterns.PASSWORD, message = ConstraintsMessageManager.PASSWORD_ERROR)
    private String confirmNewPassword;

}
