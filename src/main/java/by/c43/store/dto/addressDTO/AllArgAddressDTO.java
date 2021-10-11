package by.c43.store.dto.addressDTO;

import by.c43.store.utils.ConstraintsMessageManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AllArgAddressDTO {

    @NotBlank
    @Size(min = 3, max = 16, message = ConstraintsMessageManager.NAME_OF_COUNTRY_ERROR)
    private String country;

    @NotBlank
    @Size(min = 3, max = 16, message = ConstraintsMessageManager.NAME_OF_CITY_ERROR)
    private String city;

    @NotBlank
    @Size(min = 3, max = 16, message = ConstraintsMessageManager.NAME_OF_STREET_ERROR)
    private String street;

    @NotBlank(message = ConstraintsMessageManager.NOT_EMPTY_ERROR)
    @NotEmpty
    private String home;
}