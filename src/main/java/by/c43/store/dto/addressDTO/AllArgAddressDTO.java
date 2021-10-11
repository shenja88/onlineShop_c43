package by.c43.store.dto.addressDTO;

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
    @Size(min = 3, max = 16, message = "Name of country cannot have less then 3 and more then 16 letters")
    private String country;

    @NotBlank
    @Size(min = 3, max = 16, message = "Name of city cannot have less then 3 and more then 16 letters")
    private String city;

    @NotBlank
    @Size(min = 3, max = 16, message = "Name of street cannot have less then 3 and more then 16 letters")
    private String street;

    @NotBlank(message = "This field is required")
    @NotEmpty
    private String home;
}