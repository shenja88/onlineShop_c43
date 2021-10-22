package by.c43.store.dto.telephonesDTO;

import by.c43.store.utils.ConstraintsMessageManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumberIdTelDTO {

    private long id;
    @NotBlank
    @Size(min = 9, message = ConstraintsMessageManager.NUMBER_USER_ERROR)
    private String number;
}
