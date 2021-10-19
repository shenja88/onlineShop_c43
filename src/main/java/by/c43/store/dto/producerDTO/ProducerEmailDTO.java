package by.c43.store.dto.producerDTO;


import by.c43.store.utils.ConstraintsMessageManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProducerEmailDTO {

    @NotBlank(message = ConstraintsMessageManager.NOT_EMPTY_ERROR)
    @NotNull
    @NotEmpty
    private String email;
}
