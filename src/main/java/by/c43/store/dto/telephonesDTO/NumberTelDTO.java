package by.c43.store.dto.telephonesDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NumberTelDTO {

    @NotBlank
    @Size(min = 9, message = "Phone number cannot have less then 9 numbers")
    private String number;
}
