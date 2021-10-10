package by.c43.store.dto.telephonesDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NumberTelDTO {

    @NotBlank
    @Size(min = 9, message = "Phone number cannot have less then 9 numbers")
    private String login;
}
