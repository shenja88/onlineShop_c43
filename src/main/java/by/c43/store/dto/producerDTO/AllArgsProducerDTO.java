package by.c43.store.dto.producerDTO;

import by.c43.store.utils.ConstraintsMessageManager;
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
public class AllArgsProducerDTO {
    @NotBlank
    @Size(min = 3, max = 30, message = ConstraintsMessageManager.NAME_ERROR)
    private String name;

    @NotBlank
    @Size(min = 6, max = 100)
    @Pattern(regexp = Patterns.EMAIL, message = ConstraintsMessageManager.EMAIL_ERROR)
    private String email;

    @NotBlank
    @Pattern(regexp = Patterns.PASSWORD, message = ConstraintsMessageManager.PASSWORD_ERROR)
    private String password;

    @NotEmpty
    @NotBlank(message = ConstraintsMessageManager.NOT_EMPTY_ERROR)
    private String description;


    @NotEmpty
    @NotBlank(message = ConstraintsMessageManager.PICTURE_ERROR)
    private String picture;

    @NotEmpty
    @NotBlank(message = ConstraintsMessageManager.NOT_EMPTY_ERROR)
    private String country;

    @NotEmpty
    @NotBlank(message = ConstraintsMessageManager.NOT_EMPTY_ERROR)
    private String city;

    @NotEmpty
    @NotBlank(message = ConstraintsMessageManager.NOT_EMPTY_ERROR)
    private String street;

    @NotEmpty
    @NotBlank(message = ConstraintsMessageManager.NOT_EMPTY_ERROR)
    private String home;

    @NotBlank
    @Size(min = 9, message = ConstraintsMessageManager.NUMBER_USER_ERROR)
    private String number;
}
