package by.c43.store.dto.usersDTO;

import by.c43.store.utils.ConstraintsMessageManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class PictureUserDTO {

    @NotEmpty
    @NotBlank(message = ConstraintsMessageManager.PICTURE_ERROR)
    private String picture;

}
