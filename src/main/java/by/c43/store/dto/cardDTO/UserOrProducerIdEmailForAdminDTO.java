package by.c43.store.dto.cardDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrProducerIdEmailForAdminDTO {
    private long id;
    private String email;
}
