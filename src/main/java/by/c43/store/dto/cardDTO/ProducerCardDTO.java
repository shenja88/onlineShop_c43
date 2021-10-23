package by.c43.store.dto.cardDTO;


import by.c43.store.entity.Address;
import by.c43.store.entity.Telephone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProducerCardDTO {
    private String nameProducer;
    private String emailProducer;
    private String descriptionProducer;
    private String pictureProducer;
    private Address addressProducer;
    private List<Telephone> telephonesProducer;
}
