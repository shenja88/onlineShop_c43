package by.c43.store.dto.usersDTO;

import by.c43.store.utils.ConstraintsMessageManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NameUserDTO {

    @NotBlank
    @Size(min = 3, max = 30, message = ConstraintsMessageManager.NAME_ERROR)
    private String name;


}
