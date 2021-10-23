package by.c43.store.dto.cardDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCardInfoDTO {
    private String nameUser;
    private String emailUser;
    private String pictureUser;
    private String numberUser;
}
