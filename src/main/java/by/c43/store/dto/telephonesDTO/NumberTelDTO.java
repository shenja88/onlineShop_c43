package by.c43.store.dto.telephonesDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NumberTelDTO {

    @NotBlank
    @Size(min = 9, message = "Phone number cannot have less then 9 numbers")
    private String login;
}
