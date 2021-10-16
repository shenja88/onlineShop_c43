package by.c43.store.dto.producerDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProducerEmailDTO {

    @NotBlank
    @NotNull
    @NotEmpty
    private String email;
}
